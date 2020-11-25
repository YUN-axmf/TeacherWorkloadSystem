package list;

/**
 * 管理员账号类
 *
 * 成员变量：管理员用户名、管理员密码
 */
public class AdminAccount
{
    private String id;
    private String password;

    public AdminAccount()
    {

    }

    public AdminAccount(String id, String password)
    {
        this.id = id;
        this.password = password;
    }

    public String getId()
    {
        return id;
    }

    public String getPassword()
    {
        return password;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public void setPassword(String password)
    {
        this.password = password;
    }
}