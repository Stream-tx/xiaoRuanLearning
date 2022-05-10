package com.example.onlinetoolsservice.service.impl;

import com.example.onlinetoolsservice.common.Result;
import com.example.onlinetoolsservice.entity.*;
import com.example.onlinetoolsservice.repository.CodeRepository;
import com.example.onlinetoolsservice.repository.QuestionRepository;
import com.example.onlinetoolsservice.repository.ReferenceRepository;
import com.example.onlinetoolsservice.repository.UserRepository;
import com.example.onlinetoolsservice.service.AIService;
import lombok.var;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class AIServiceImpl implements AIService {

    private final CodeRepository codeRepository;
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    public AIServiceImpl(CodeRepository codeRepository,QuestionRepository questionRepository,UserRepository userRepository) {
        this.codeRepository = codeRepository;
        this.questionRepository = questionRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Result questionRecommendation(@RequestParam Long userId){
        return Result.success(null);
    }

    @Override
    public Result addChatRecords(@RequestBody Chat chat) {
        return Result.success(null);
    }

    @Override
    public Result deleteChatRecords(@RequestParam Long chatId) {
        return Result.success(null);
    }

    @Override
    public Result findChatRecords(@RequestParam String content){
        return Result.success(null);
    }

    @Override
    public Result listChatRecordsByUserId(@RequestParam Long userId){
        return Result.success(null);
    }

    @Override
    public QuestionEntity recommendquestion(User user)
    {

        String submissionList = user.getSubmissionList();
        String[] submissionQuestions = submissionList.split(",");
        List<CodeEntity> submissionCode = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        List<String> questionLable = new ArrayList<>();

        if (!submissionList.equals("")) {
            for (String pQuestion : submissionQuestions) {
                long cid = Long.parseLong(pQuestion);
                CodeEntity code = codeRepository.findById(cid).orElse(null);
//                System.out.println(code);
                if (code != null) {
                    submissionCode.add(code);
                    questionRepository.findById(code.getQuestionId()).ifPresent(questionList::add);
                    if(code.getState()==0) {
                        QuestionEntity question = questionRepository.findById(code.getQuestionId()).orElse(null);

                        if (question != null) {
                            String[] questionLables = question.getLabels().split(",");
                            for (String str : questionLables) {
                                questionLable.add(str);
                            }

                        }
                    }
                }
            }
        }
        HashMap<String, Integer> map = new HashMap<>();
        for (String obj : questionLable) {
//            if (map.containsKey(obj)) {
//                count++;
            Integer count = map.get(obj);
            map.put(obj, (count == null) ? 1 : count + 1);
//                map.put(obj, map.get(obj) + 1);
//            }
//            else {
//                map.put(obj, 1);
//            }
        }

        int maxCount =0;
        String res=" ";
        for(var key: map.keySet()){
            if(map.get(key)>maxCount){
                maxCount=map.get(key);
                res=key;
            }
        }
        System.out.println(res);
        List<QuestionEntity> questions=questionRepository.listQuestion();
        questions.removeAll(questionList);
        for(QuestionEntity qus:questions){
            if(res!=null) {
                System.out.println(qus.getQuestionId());
                System.out.println(qus.getLabels());
                if (qus.getLabels().contains(res)) {
                    return qus;
                }
            }
            else {
                return qus;
            }
        }
        return null;
    }

    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public List<QuestionEntity> recommendquestion2(String username){
//
//        String submissionList = user.getSubmissionList();
//        String[] submissionQuestions = submissionList.split(",");
//        List<CodeEntity> submissionCode = new ArrayList<>();
//        List<QuestionEntity> questionList = new ArrayList<>();
//        if (!submissionList.equals("")) {
//            for (String pQuestion : submissionQuestions) {
//                long cid = Long.parseLong(pQuestion);
//                CodeEntity code = codeRepository.findById(cid).orElse(null);
//                if (code != null) {
//                    submissionCode.add(code);
//                    questionRepository.findById(code.getQuestionId()).ifPresent(questionList::add);
//                }
//            }
//        }
//        Map<Double, String> distances = new TreeMap<>();

//
//
//        for (int i = 0; i < users.size(); i++) {

//            String submissionList2 = u2.getSubmissionList();
//            String[] submissionQuestions2 = submissionList2.split(",");
//            List<CodeEntity> submissionCode2 = new ArrayList<>();
//            List<QuestionEntity> questionList2 = new ArrayList<>();
//            if (!submissionList2.equals("")) {
//                for (String pQuestion : submissionQuestions2) {
//                    long cid = Long.parseLong(pQuestion);
//                    CodeEntity code = codeRepository.findById(cid).orElse(null);
//                    if (code != null) {
//                        submissionCode2.add(code);
//                        questionRepository.findById(code.getQuestionId()).ifPresent(questionList2::add);
//                    }
//                }
//            }
//            var id=u2.getUserId();
//            var id2=user.getUserId();
//            if (id!=id2) {
//                double distance = pearson_dis(submissionCode2, submissionCode);
//                distances.put(distance, u2.getUsername());
//            }
//        }
//        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
//
//        return distances;
        List<User> users=userRepository.findAll();
        for(User u:users){
            System.out.println("id="+u.getUserId());
        }
        Map<Double, String> distances = computeNearestNeighbor(username, users);
        double max=-1;
        String nearest="";
        for (Map.Entry<Double, String> entry : distances.entrySet()) {
            Double mapKey = entry.getKey();
            if(mapKey>max){
                max=mapKey;
                nearest=entry.getValue();
            }
        }
        //String nearest = distances.values().iterator().next();
        System.out.println("最近邻 -> " + nearest);

        //找到最近邻看过，但是我们没看过的电影，计算推荐
        User neighborRatings = new User();
        for (User user:users) {
            if (nearest.equals(user.getUsername())) {
                neighborRatings = user;
            }
        }
        //System.out.println("最近做过的题目 -> " + neighborRatings.getSubmissionList());

        User userRatings = new User();
        for (User user:users) {
            if (username.equals(user.getUsername())) {
                userRatings = user;
            }
        }
        //System.out.println("用户看过的电影 -> " + userRatings.getSubmissionList());

        String submissionList = neighborRatings.getSubmissionList();
        String[] submissionQuestions = submissionList.split(",");
        List<CodeEntity> submissionCode = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        if (!submissionList.equals("")) {
            for (String pQuestion : submissionQuestions) {
                long cid = Long.parseLong(pQuestion);
                CodeEntity code = codeRepository.findById(cid).orElse(null);
                if (code != null) {
                    submissionCode.add(code);
                    questionRepository.findById(code.getQuestionId()).ifPresent(questionList::add);
                }
            }
        }
        System.out.println("最近做过的题目 -> " );
        for(QuestionEntity q :questionList){
            System.out.println(q.getQuestionId() );
        }
        String submissionList2 = userRatings.getSubmissionList();
        String[] submissionQuestions2 = submissionList2.split(",");
        List<CodeEntity> submissionCode2 = new ArrayList<>();
        List<QuestionEntity> questionList2 = new ArrayList<>();
        if (!submissionList2.equals("")) {
            for (String pQuestion : submissionQuestions2) {
                long cid = Long.parseLong(pQuestion);
                CodeEntity code = codeRepository.findById(cid).orElse(null);
                if (code != null) {
                    submissionCode2.add(code);
                    questionRepository.findById(code.getQuestionId()).ifPresent(questionList2::add);
                }
            }
        }
        System.out.println("用户做过的题目 -> " );
        for(QuestionEntity q :questionList2){
            System.out.println(q.getQuestionId() );
        }
        questionList2.removeAll(questionList);

        // Collections.sort(questionList2);
        return questionList2;

    }
    private Map<Double, String> computeNearestNeighbor(String username, List<User> users){
        User user=userRepository.findByUsername(username);
        String submissionList = user.getSubmissionList();
        String[] submissionQuestions = submissionList.split(",");
        List<CodeEntity> submissionCode = new ArrayList<>();
        List<QuestionEntity> questionList = new ArrayList<>();
        if (!submissionList.equals("")) {
            for (String pQuestion : submissionQuestions) {
                long cid = Long.parseLong(pQuestion);
                CodeEntity code = codeRepository.findById(cid).orElse(null);
                if (code != null) {
                    submissionCode.add(code);
                    questionRepository.findById(code.getQuestionId()).ifPresent(questionList::add);
                }
            }
        }
        Map<Double, String> distances = new TreeMap<>();


        for (int i = 0; i < users.size(); i++) {
            User u2 = users.get(i);
            String submissionList2 = u2.getSubmissionList();
            String[] submissionQuestions2 = submissionList2.split(",");
            List<CodeEntity> submissionCode2 = new ArrayList<>();
            List<QuestionEntity> questionList2 = new ArrayList<>();
            if (!submissionList2.equals("")) {
                for (String pQuestion : submissionQuestions2) {
                    long cid = Long.parseLong(pQuestion);
                    CodeEntity code = codeRepository.findById(cid).orElse(null);
                    if (code != null) {
                        submissionCode2.add(code);
                        questionRepository.findById(code.getQuestionId()).ifPresent(questionList2::add);
                    }
                }
            }
            var id=u2.getUserId();
            var id2=user.getUserId();
            if (id!=id2) {
                System.out.println("id: " + id+"id1: "+id2+"name:"+u2.getUsername());
//                if(submissionCode2.size()>submissionCode.size()){
//                    for(i=0;i<submissionCode2.size()-submissionCode.size();i++){
//                        submissionCode2.remove(i+1);
//                    }
//                }
//                if(submissionCode2.size()<submissionCode.size()){
//                    for(i=0;i<submissionCode.size()-submissionCode2.size();i++){
//                        submissionCode.remove(i+1);
//                    }
//                }
                double distance = pearson_dis(submissionCode2, submissionCode);
                distances.put(distance, u2.getUsername());
                System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
            }
        }
        System.out.println("该用户与其他用户的皮尔森相关系数 -> " + distances);
        return distances;
    }


    private double pearson_dis(List<CodeEntity> rating1, List<CodeEntity> rating2) {
        int n=rating2.size();
        if(rating1.size()<rating2.size()){
            n=rating1.size();
        }
        List<Integer> rating1ScoreCollect = rating1.stream().map(A -> A.getState()).collect(Collectors.toList());
        List<Integer> rating2ScoreCollect = rating2.stream().map(A -> A.getState()).collect(Collectors.toList());
        for(CodeEntity code:rating1)
        {
            System.out.println(code.getQuestionId());
        }
        for(CodeEntity code:rating2)
        {
            System.out.println(code.getQuestionId());
        }
        System.out.println(rating1ScoreCollect);
        System.out.println(rating2ScoreCollect);
        double Ex= rating1ScoreCollect.stream().mapToDouble(x->x).sum();
        double Ey= rating2ScoreCollect.stream().mapToDouble(y->y).sum();
        double Ex2=rating1ScoreCollect.stream().mapToDouble(x->Math.pow(x,2)).sum();
        double Ey2=rating2ScoreCollect.stream().mapToDouble(y->Math.pow(y,2)).sum();
        double Exy= IntStream.range(0,n).mapToDouble(i->rating1ScoreCollect.get(i)*rating2ScoreCollect.get(i)).sum();
        double numerator=Exy-Ex*Ey/n;
        double denominator=Math.sqrt((Ex2-Math.pow(Ex,2)/n)*(Ey2-Math.pow(Ey,2)/n));
        if (denominator==0) return 0.0;
        return numerator/denominator;
    }

}
