package bit.work.shop.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;

import bit.work.shop.action.Base.BaseAction;
import bit.work.shop.dao.UserDao;
import bit.work.shop.domain.User;

public class FileUploadAction extends BaseAction{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String bgname;
    
    //注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File file;
    
    //提交过来的file的名字
    private String fileFileName;
    
    //提交过来的file的MIME类型
    private String fileContentType;

	private UserDao dao;
    
    // 文件上传 
    public String fileUpload() throws Exception {

    	String root = ServletActionContext.getServletContext().getRealPath("/img");
    	String rmstr= UUID.randomUUID().toString()+fileFileName.substring(fileFileName.lastIndexOf("."));
    	
        InputStream is = new FileInputStream(file);
        
        OutputStream os = new FileOutputStream(new File(root, rmstr));
        
        byte[] buffer = new byte[1024];
        int length = 0;
        
        while((length = is.read(buffer))>0)
        {
            os.write(buffer,0,length);
        }
        
        os.close();
        is.close();
        
        
        // 信息插入数据库中
        ActionContext ctx = ActionContext.getContext();
		User curUser = (User) ctx.getSession().get("curuser");
        dao = new UserDao();
        int rows = dao.provideBg(bgname, rmstr, curUser.getUid());
        
        return "bgs";
    }
    
    //	-----------------------------------------

    public File getFile()
    {
        return file;
    }

    public String getBgname() {
		return bgname;
	}

	public void setBgname(String bgname) {
		this.bgname = bgname;
	}

	public void setFile(File file)
    {
        this.file = file;
    }

    public String getFileFileName()
    {
        return fileFileName;
    }

    public void setFileFileName(String fileFileName)
    {
        this.fileFileName = fileFileName;
    }

    public String getFileContentType()
    {
        return fileContentType;
    }

    public void setFileContentType(String fileContentType)
    {
        this.fileContentType = fileContentType;
    }
    
}