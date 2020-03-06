public class TimeDayEvent {
    private String date;

    public TimeDayEvent(String date){
        this.date = date;


    }
    public void addAlert(){
        Alert.addAlert();
    }

    public void deleteAlert(){
        Alert.deleteAlert();
    }

    public void editAlert(){
        Alert.editAlert();
    }
}
