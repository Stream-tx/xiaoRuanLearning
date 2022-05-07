package com.example.backend.entity;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;

@Entity
@Table(name = "user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;
    @NotBlank(message = "The username can't be blank")
    private String username;
    @NotBlank(message = "The password can't be blank")
    private String password;
    @NotBlank(message = "The email can't be blank")
    @Email(message = "The format of email isn't correct")
    private String email;
    private String university;
    private byte[] avatar;
    private String profile;
    private String submissionList;
    private Integer age;

    public User() {

    }

    public User(String username, String password, String email, String university, Integer age) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.university = university;
        this.age = age;
        try {
            String path = Objects.requireNonNull(User.class.getClassLoader().getResource("")).getPath().split("target")[0] + "src/main/resources/photo/avatar.jpg";
            this.avatar = getByte(new File(path));
        } catch (Exception e) {
            e.printStackTrace();
        }
        profile = "";
        submissionList = "";
    }

    public byte[] getByte(File file) throws Exception {
        byte[] bytes = null;
        if (file != null) {
            InputStream is = new FileInputStream(file);
            int length = (int) file.length();
            bytes = new byte[length];
            int offset = 0;
            int numRead;
            while (offset < bytes.length && (numRead = is.read(bytes, offset, bytes.length - offset)) >= 0) {
                offset += numRead;
            }
            if (offset < bytes.length) {
                System.out.println("file length is error");
                return null;
            }
            is.close();
        }
        return bytes;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public byte[] getAvatar() {
        return avatar;
    }

    public void setAvatar(byte[] avatar) {
        this.avatar = avatar;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }

    public String getSubmissionList() {
        return submissionList;
    }

    public void setSubmissionList(String submissionList) {
        this.submissionList = submissionList;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", university='" + university + '\'' +
                ", avatar=" + Arrays.toString(avatar) +
                ", profile='" + profile + '\'' +
                ", submissionList='" + submissionList + '\'' +
                ", age=" + age +
                '}';
    }
}
