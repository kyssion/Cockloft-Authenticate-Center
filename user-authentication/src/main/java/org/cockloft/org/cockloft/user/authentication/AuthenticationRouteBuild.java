package org.cockloft.org.cockloft.user.authentication;

import io.vertx.core.http.HttpMethod;
import org.cockloft.org.cockloft.user.authentication.handle.LoginHandle;
import org.cockloft.vertx.router.Router;

public class AuthenticationRouteBuild {
    public static void build(Router router){
        router.route("/login", HttpMethod.POST).handle(new LoginHandle());
    }
}
