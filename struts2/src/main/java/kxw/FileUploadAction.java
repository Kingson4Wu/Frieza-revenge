package kxw;
import java.io.File;

import java.io.FileInputStream;

import java.io.FileOutputStream;

import java.io.InputStream;

import java.io.OutputStream;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")

public class FileUploadAction extends ActionSupport {

    private List<File> file;

    private List<String> fileContentType;

    private List<String> fileFileName;

    private String memo;

    @Override

    public String execute() throws Exception {


        HttpServletRequest request = ServletActionContext.getRequest();
        String path = request.getSession().getServletContext().getRealPath("/kxw");
//保存目录为kxw



        if(file==null)

        {

            this.addFieldError("file", "文件不能为空，请选择");

            return INPUT;

        }else

        {

            InputStream is = null;

            OutputStream os = null;

            File f=null;

            long fileSize=0;

            String ext="";

            for (int i = 0; i < file.size(); i++) {

                ext=fileFileName.get(i).substring(fileFileName.get(i).lastIndexOf(".")+1);

                f=this.getFile().get(i);

                fileSize=f.length();

                System.out.println("fileSize:"+fileSize);

                System.out.println("ext:"+ext);

                if("exe".equals(ext)||"jar".equals(ext)||"bat".equals(ext)||"msi".equals(ext))

                {

                    this.addFieldError("file", "the file is not allowed");

                    return INPUT;

                }



                if(fileSize>102000)

                {

                    this.addFieldError("file", "the file is too large");

                    return INPUT;

                }



                is=new FileInputStream(f);

                os=new FileOutputStream(new File(path,this.getFileFileName().get(i)));

                byte[] buf=new byte[1024];

                int length=0;

                while((length=is.read(buf))>0)

                {

                    os.write(buf, 0, length);

                }

            }

            is.close();

            os.close();

        }

        return SUCCESS;

    }

    public List<File> getFile() {

        return file;

    }

    public void setFile(List<File> file) {

        this.file = file;

    }

    public List<String> getFileContentType() {

        return fileContentType;

    }

    public void setFileContentType(List<String> fileContentType) {

        this.fileContentType = fileContentType;

    }

    public List<String> getFileFileName() {

        return fileFileName;

    }

    public void setFileFileName(List<String> fileFileName) {

        this.fileFileName = fileFileName;

    }

    public String getMemo() {

        return memo;

    }

    public void setMemo(String memo) {

        this.memo = memo;

    }

}