package cn.rkyang.community.dto;

/**
 * GitHub用户数据传输对象
 * @author Rkyang
 */
public class GitHubUserDTO {

    /**
     * 用户名
     */
    private String name;

    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户bio
     */
    private String bio;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    @Override
    public String toString() {
        return "GitHubUserDTO{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                '}';
    }
}
