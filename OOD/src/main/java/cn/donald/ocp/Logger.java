package cn.donald.ocp;

/**
 * Created by DonaldY on 2017/6/21.
 */
public class Logger {

    private Formatter formattor;
    private Sender sender;

    public Logger(Formatter formattor, Sender sender){
        this.formattor = formattor;
        this.sender = sender;
    }

    public void Sender(String msg){

        this.sender.send(formattor.format(msg));

    }
}

