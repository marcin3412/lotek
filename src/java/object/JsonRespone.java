/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package object;

import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author rekah4
 */
public class JsonRespone {
    JSONObject resp = new JSONObject();
    public String callback = "callback";
    
  public void setStatus(boolean flag){
      if(flag)
          try {
              resp.put("status", "OK");
      } catch (JSONException ex) {
          Logger.getLogger(JsonRespone.class.getName()).log(Level.SEVERE, null, ex);
      }
      else
          try {
              resp.put("status", "ERROR");
      } catch (JSONException ex) {
          Logger.getLogger(JsonRespone.class.getName()).log(Level.SEVERE, null, ex);
      }
  }
  
  public void setMsg(String msg){
        try {
            resp.put("msg", msg);
        } catch (JSONException ex) {
            Logger.getLogger(JsonRespone.class.getName()).log(Level.SEVERE, null, ex);
        }
  }
  
  public String Flush(JSONObject data){
        try {
            resp.put("data", data);
        } catch (JSONException ex) {
            Logger.getLogger(JsonRespone.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return this.callback+"("+resp+")";
  }
  
  public boolean isError(){
        try {
            if(resp.getString("status").equals("OK"))
                return false;
            else
                return true;
        } catch (JSONException ex) {
            Logger.getLogger(JsonRespone.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
  }
}
