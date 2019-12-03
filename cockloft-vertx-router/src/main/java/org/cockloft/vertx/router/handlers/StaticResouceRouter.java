package org.cockloft.vertx.router.handlers;

import io.vertx.core.Handler;
import io.vertx.core.http.HttpServerRequest;

import java.io.IOException;
import java.io.InputStream;

public class StaticResouceRouter implements Handler<HttpServerRequest> {

    private String staticPath="";

    public StaticResouceRouter(String path){
        this.staticPath = path;
    }

    @Override
    public void handle(HttpServerRequest request) {
        String path = request.path().substring(1);
        if(!isLegitimateResources(path)){
            request.response().end("no resource");
        }
        InputStream inputStream = StaticResouceRouter.class.getClassLoader().getResourceAsStream(this.staticPath+path);
        if(inputStream==null){
            request.response().end();
            return;
        }
        byte[] bytes = new byte[126];
        StringBuilder builder = new StringBuilder();
        int length = 0;
        while(true){
            try {
                if ((length=inputStream.read(bytes))==-1){
                    break;
                }
                builder.append(new String(bytes,0,length));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        request.response().end(builder.toString());
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
}
