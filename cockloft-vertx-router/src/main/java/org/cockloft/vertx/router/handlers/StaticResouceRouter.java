package org.cockloft.vertx.router.handlers;

import org.cockloft.vertx.router.RouteContext;

import java.io.IOException;
import java.io.InputStream;

public class StaticResouceRouter implements RouterHandler<RouteContext> {

    private String staticPath="";

    @Override
    public void handle(RouteContext routeContext) {
        String path = routeContext.getPath();
        if(!isLegitimateResources(path)){
            routeContext.getResponse().end("no resource");
        }
        InputStream inputStream = StaticResouceRouter.class.getClassLoader().getResourceAsStream(this.staticPath+path);
        byte[] bytes = new byte[126];
        StringBuilder builder = new StringBuilder();
        int length = 0;
        while(true){
            try {
                if ((length=inputStream.read(bytes))==0){
                    break;
                }
                builder.append(new String(bytes,0,length));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        routeContext.getResponse().end(builder.toString());
    }

    public boolean isLegitimateResources(String path){
        int index = -1;
        for(int a=0;a<path.length();a++){
            if(path.charAt(a)=='.'){
                if(index==-1){
                    index = a;
                }else{
                    return false;
                }
            }
        }
        return index != -1;
    }

    public String getStaticPath() {
        return staticPath;
    }

    public void setStaticPath(String staticPath) {
        this.staticPath = staticPath;
    }
}
