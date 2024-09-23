package handy.api

class UrlMappings {
    static mappings = {
        delete "/$controller/$id(.$format)?"(action:"delete")
        get "/$controller(.$format)?"(action:"index")
        get "/$controller/$id(.$format)?"(action:"show")
        post "/$controller(.$format)?"(action:"save")
        put "/$controller/$id(.$format)?"(action:"update")
        patch "/$controller/$id(.$format)?"(action:"patch")
        "/"(controller: 'application', action:'index')

        "/login/register"(controller: "userRegistration", action: "register", method: "POST")

        "/list"(controller: "userRegistration", action: "listUsers", method: "GET")

        "/verifyAccount"(controller: "userRegistration", action: "verifyAccount", method: "GET")


        "500"(view:'/error')
        "404"(view:'/notFound')

    }
}
