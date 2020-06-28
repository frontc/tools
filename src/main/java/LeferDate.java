import java.util.Date;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * @author : lefer
 * @version : V1.0
 * @date :   2020/6/28
 * @Description : 日期相关处理工具方法
 */
public class LeferDate {
    public static Date today(){
        return Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
    }
}
