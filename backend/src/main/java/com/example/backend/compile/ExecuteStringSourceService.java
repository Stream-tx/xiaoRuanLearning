package com.example.backend.compile;

import com.example.backend.entity.Question;
import org.springframework.stereotype.Service;

import javax.tools.Diagnostic;
import javax.tools.DiagnosticCollector;
import javax.tools.JavaFileObject;
import java.util.List;

import java.util.concurrent.*;

@Service
public class ExecuteStringSourceService {

    private static final int RUN_TIME_LIMITED = 15;

    private static final int N_THREAD = 9;

    private static final ExecutorService pool = new ThreadPoolExecutor(N_THREAD, N_THREAD,
            0L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(N_THREAD));

    private static final String WAIT_WARNING = "服务器忙，请稍后提交";
    private static final String NO_OUTPUT = "Nothing.";


    public String execute(String source, String systemIn, Question question) {
        if (!systemIn.equals("nullabc"))
            source = handle(source, systemIn, question);
        DiagnosticCollector<JavaFileObject> compileCollector = new DiagnosticCollector<>(); // 编译结果收集器

        // 编译源代码
        byte[] classBytes = StringSourceCompiler.compile(source, compileCollector);

        // 编译不通过，获取并返回编译错误信息
        if (classBytes == null) {
            // 获取编译错误信息
            List<Diagnostic<? extends JavaFileObject>> compileError = compileCollector.getDiagnostics();
            StringBuilder compileErrorRes = new StringBuilder();
            for (Diagnostic diagnostic : compileError) {
                compileErrorRes.append("Compilation error at ");
                compileErrorRes.append(diagnostic.getLineNumber());
                compileErrorRes.append(".");
                compileErrorRes.append(System.lineSeparator());
            }
            return compileErrorRes.toString();
        }

        // 运行字节码的main方法
        Callable<String> runTask = () -> JavaClassExecutor.execute(classBytes);
        Future<String> res;
        try {
            res = pool.submit(runTask);
        } catch (RejectedExecutionException e) {
            return WAIT_WARNING;
        }

        // 获取运行结果，处理非客户端代码错误
        String runResult;
        try {
            runResult = res.get(RUN_TIME_LIMITED, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            runResult = "Program interrupted.";
        } catch (ExecutionException e) {
            runResult = e.getCause().getMessage();
        } catch (TimeoutException e) {
            runResult = "Time Limit Exceeded.";
        } finally {
            res.cancel(true);
        }
        return runResult != null ? runResult.replaceAll(System.lineSeparator(), "") : NO_OUTPUT;
    }

    public String handle(String source, String input, Question question) {
        String returnOutput = "";
        String suffix = "";
        String returnType = question.getReturnType();
        if (returnType.contains("[")) {
            returnOutput = "Arrays.toString(";
            suffix = ")";
        }
        input = input.replace("[", "{").replace("]", "}");
        input = input.replace("{\"", "new String[]{\"");
        if (!input.contains("String"))
            input = input.replace("{", "new int[]{");
        int index = source.indexOf('{');
        source = "import java.util.*;\n\n" + source.substring(0, index + 1) + "\n    public static void main(String[] args) {\n" +
                "        System.out.println(" + returnOutput + "new Run().run(" + input + ")" + suffix + ");\n" +
                "    }" + source.substring(index + 1);
        return source;

    }
}
