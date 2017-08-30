package classes;

public class Constants {

    //Dynamic Scanner Related Constants
    public final static String ZAP_DOCKER_IMAGE_NAME = "owasp/zap2docker-stable:latest";
    public final static String ZAP_HOST_PORT = "8082";
    public final static String ZAP_CONTAINER_PORT = "8082";
    public final static String ZAP_IP_ADDRESS = "0.0.0.0";
    public final static String[] ZAP_CONTAINER_STARTUP_COMMAND = {"zap.sh", "-daemon", "-port", "8081", "-host", "0.0.0.0", "-config", "api.disablekey=true", "-config", "api.addrs.addr.name=.*",
            "-config", "api.addrs.addr.regex=true"};


    //Static Scanner Related Constants
    public final static String STATIC_SCANNER_IMAGE_NAME = "dessi/static_scanner";
    public final static String STATIC_SCANNER_HOST_PORT = "8081";
    public final static String STATIC_SCANNER_CONTAINER_PORT = "8081";
    public final static String STATIC_SCANNER_IP_ADDRESS = "0.0.0.0";

    public final static String STATIC_SCANNER = "static";
    public final static String DYNAMIC_SCANNER = "dynamic";


    public final static String CLONE_URL = "http://localhost:8081/staticScanner/cloneProduct";


    public final static String RUNNING_STATE = "running";
    public final static String STOPPED_STATE = "stopped";
    public final static String NOT_STARTED_STATE = "not started";

    public final static String SCAN_TYPE = "scanType";
    public final static String FIND_SECURITY_BUGS = "FindSecBugs";
    public static final String DEPENDENCY_CHECK = "Dependency Check";
    public static final String ZAP = "Zap";

    public final static String FIND_SEC_BUGS_REPORTS = "Find-Sec-Bugs-Reports";
    public final static String DEPENDENCY_CHECK_REPORTS = "Dependency-Check-Reports";
    public final static String ZAP_REPORT = "zap-reports";

    public final static String ZIP_FILE_EXTENSION = ".zip";
}
