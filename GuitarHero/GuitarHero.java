public class GuitarHero {

    public static void main(String[] args){
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";
        GuitarString[] notes = new GuitarString[37];
        for(int i = 0; i < keyboard.length(); i++){
            double frequency = 440 * Math.pow(1.05956, i - 24);
            notes[i] = new GuitarString(frequency);
        }

        while(true){

            //Check if user typed key
            if(StdDraw.hasNextKeyTyped()){
                //Get character typed by user and play corresponding string
                char key = StdDraw.nextKeyTyped();
                int index = keyboard.indexOf(key);
                if(index != -1){
                    notes[index].pluck();
                }
            }

            //Superposition of samples
            double sample = 0;
            for(int i = 0; i < notes.length; i++){
                sample += notes[i].sample();
            }

            //Send result to StdAudio
            StdAudio.play(sample);

            //Advance simulation of each guitar string one step
            for(int i = 0; i < notes.length; i++){
                notes[i].tic();
            }
        }
    }
}
