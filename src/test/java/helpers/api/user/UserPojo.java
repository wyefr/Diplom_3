package helpers.api.user;

public class UserPojo {
    private String email;
    private String password;
    private String name;

    public UserPojo(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
    }

    public UserPojo (String email, String password) {
        this.email = email;
        this.password = password;
    }

    public UserPojo (String email) {
        this.email = email;
    }

    public UserPojo () {
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        if (email == null) {
            throw new IllegalArgumentException("Email cannot be null");
        }
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        if (password == null) {
            throw new IllegalArgumentException("Password cannot be null");
        }
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Name cannot be null");
        }
        this.name = name;
    }
}
