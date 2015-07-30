package in.frodo.dropwizard;

import java.net.SocketException;
import java.util.EnumSet;

import javax.servlet.DispatcherType;

import org.eclipse.jetty.servlets.CrossOriginFilter;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.ScannerFactory;
import com.wordnik.swagger.config.SwaggerConfig;
import com.wordnik.swagger.jaxrs.config.DefaultJaxrsScanner;
import com.wordnik.swagger.jaxrs.listing.ApiDeclarationProvider;
import com.wordnik.swagger.jaxrs.listing.ApiListingResourceJSON;
import com.wordnik.swagger.jaxrs.listing.ResourceListingProvider;
import com.wordnik.swagger.jaxrs.reader.DefaultJaxrsApiReader;
import com.wordnik.swagger.model.ApiInfo;
import com.wordnik.swagger.reader.ClassReaders;

import in.frodo.dropwizard.resources.GreetingsResource;
import io.dropwizard.Application;
//import io.dropwizard.assets.AssetsBundle;
//import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;



public class DWApplication extends Application<DWConfig> {

  /*@Override
  public void initialize(Bootstrap<DWConfig> bootstrap) {
      
      bootstrap.addBundle(new AssetsBundle("/assets/", "/docs", "index.htm"));
  }*/
  
  public static void main(String[] args) throws Exception {
    new DWApplication().run(args);
  }
  
  @Override
  public void run(DWConfig configuration, Environment environment) throws Exception {
    // TODO Auto-generated method stub
    
    environment.jersey().register(new GreetingsResource());
    configureSwagger(environment);
  }
  
  private void configureSwagger(Environment environment) throws SocketException {

    // Swagger Resource
    environment.jersey().register(new ApiListingResourceJSON());

    // Swagger providers
    environment.jersey().register(new ApiDeclarationProvider());
    environment.jersey().register(new ResourceListingProvider());

    // Swagger Scanner, which finds all the resources for @Api Annotations
    ScannerFactory.setScanner(new DefaultJaxrsScanner());

    // Add the reader, which scans the resources and extracts the resource
    // information
    ClassReaders.setReader(new DefaultJaxrsApiReader());

    // Set the swagger config options
    ApiInfo apiInfo = new ApiInfo("Product Service", "API to access product service", "", "", "", "");
    
    SwaggerConfig config = ConfigFactory.config();
    config.setApiVersion("1.0.1");
    config.setBasePath("./");
    

    environment
            .servlets()
            .addFilter("CORS", CrossOriginFilter.class)
            .addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class),
                    true, "/*");
  }

}
