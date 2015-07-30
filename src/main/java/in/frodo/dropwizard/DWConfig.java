package in.frodo.dropwizard;

import io.dropwizard.Configuration;

public class DWConfig extends Configuration {
  
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
  
}
