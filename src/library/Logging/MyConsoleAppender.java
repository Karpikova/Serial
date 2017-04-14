package library.Logging;

import org.apache.log4j.ConsoleAppender;
import org.apache.log4j.WriterAppender;
import org.apache.log4j.spi.LoggingEvent;

/*
 * ${Classname}
 * 
 * Version 1.0 
 * 
 * 14.04.2017
 * 
 * Karpikova
 */
public class MyConsoleAppender extends WriterAppender {
    private static MailLog mail;
    static {
        mail = new MailLog();
    }

    @Override
    public void append(LoggingEvent var1){
        mail.sendMessage(var1.getMessage().toString());
    }
}
