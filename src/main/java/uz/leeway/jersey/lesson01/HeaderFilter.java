//package uz.leeway.jersey.lesson01;
//
//import javax.ws.rs.container.ContainerRequestContext;
//import javax.ws.rs.container.ContainerResponseContext;
//import javax.ws.rs.container.ContainerResponseFilter;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.ext.Provider;
//import java.io.IOException;
//
//@Provider
//public class HeaderFilter implements ContainerResponseFilter {
//
//    @Override
//    public void filter(ContainerRequestContext requestContext,
//                       ContainerResponseContext responseContext) throws IOException {
//        String lang = requestContext.getHeaderString("lang");
//
//        if (lang.equals("uz")) {
//            responseContext.setEntity("O'zbekiston",null,MediaType.APPLICATION_JSON_TYPE);
//        } else if (lang.equals("ru")) {
//            responseContext.setEntity("Russian",null,MediaType.APPLICATION_JSON_TYPE);
//        } else {
//            responseContext.setEntity("Other language",null,MediaType.APPLICATION_JSON_TYPE);
//        }
//    }
//}
