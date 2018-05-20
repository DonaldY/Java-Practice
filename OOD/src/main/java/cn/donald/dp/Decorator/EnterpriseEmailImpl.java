package cn.donald.dp.Decorator;

/**
 * Created by DonaldY on 2017/8/14.
 */
public class EnterpriseEmailImpl implements Email{
    private String content;
    private Email email;

    public EnterpriseEmailImpl(Email email) {
        this.email = email;
        this.content = "Only person, not enterprise!";
    }
    
    @Override
    public String getContent() {
        return email.getContent() + content;
    }
}
