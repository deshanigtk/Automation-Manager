package classes;

public class Constant {

    //Dynamic Scanner Related Constants
    public final static String ZAP_DOCKER_IMAGE_NAME="owasp/zap2docker-stable:latest";
    public final static String ZAP_HOST_PORT="8082";
    public final static String ZAP_CONTAINER_PORT="8082";
    public final static String ZAP_IP_ADDRESS="0.0.0.0";
    public final static String[] ZAP_CONTAINER_STARTUP_COMMAND={"zap.sh", "-daemon", "-port", "8081", "-host", "0.0.0.0", "-config", "api.disablekey=true", "-config", "api.addrs.addr.name=.*",
            "-config", "api.addrs.addr.regex=true"};


    //Static Scanner Related Constants
    public final static String STATIC_SCANNER_IMAGE_NAME="dessi/automationmanager";
    public final static String STATIC_SCANNER_HOST_PORT="8081";
    public final static String STATIC_SCANNER_CONTAINER_PORT="8081";
    public final static String STATIC_SCANNER_IP_ADDRESS="0.0.0.0";

    public final static String STATIC_SCANNER="static";
    public final static String DYNAMIC_SCANNER="dynamic";


    public final static String CLONE_URL="http://localhost:8081/staticScanner/runScan/cloneProduct";


    public final static String RUNNING_STATE="running";
    public final static String STOPPED_STATE="stopped";
    public final static String NOT_STARTED_STATE="not started";
}
