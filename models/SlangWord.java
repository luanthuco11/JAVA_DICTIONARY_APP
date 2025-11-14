package models;

public class SlangWord {
   private String _slang;
   private String _mean;

   public SlangWord(String slang, String mean){
        this._slang = slang;
        this._mean = mean;
    }

    public String getSlang(){
         return _slang;
    }
    public String getMean(){
        return _mean;
    }
    public void setMean(String mean){
        _mean = mean;
    }

}
