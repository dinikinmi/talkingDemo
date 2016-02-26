package Global;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.Serializable;

public class ChatObject implements Serializable {
public String fromId;
public String toId;
public String Time;
public String Content;
public String serverPath;
public String msgType;
public String localPath;
public String Duration;
public Long fileLong;
public FileOutputStream fos;
public byte fileByte[];
public FileInputStream fis ;
}
