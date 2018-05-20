package cn.donald.dp.Decorator;

/**
 * Created by DonaldY on 2017/8/14.
 */
public class EmailImpl implements Email{

    private String content;

    public EmailImpl(String content) {
        this.content = content;
    }

    @Override
    public String getContent() {
        return this.content;
    }

}
