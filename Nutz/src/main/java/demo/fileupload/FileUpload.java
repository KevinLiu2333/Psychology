package demo.fileupload;

import org.nutz.mvc.annotation.AdaptBy;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Param;
import org.nutz.mvc.upload.UploadAdaptor;

import java.io.File;

/**
 * Created with IntelliJ IDEA.
 * User: Administrator
 * Date: 2017/7/24 0024
 * Time: 16:26
 */
public class FileUpload {


    @At
    //默认处理 enctype="multipart/form-data" method="post" 指定临时文件夹
    @AdaptBy(type = UploadAdaptor.class, args = {"${app.root}/WEB-INF/tmp"})
    //多文件上传同一name时需要用TemFile[]进行接收
    public void uploadPhoto(@Param("id")int id,@Param("photo")File f) {

    }
}
