public class FrequencyAlert extends Alert {
    private String frequency;
    public FrequencyAlert(String description, String date, Boolean repeat, String frequency) {
        super(description, date, repeat);

        if (repeat){
            this.frequency = frequency;
        }
        else {
            this.frequency = "N/A";
        }

    }

}
