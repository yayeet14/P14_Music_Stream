package tp.edu.sg.p14musicstream;

public class SongCollection {
    public Song allSongs[] = new Song[5];

    public SongCollection() {
        Song theWayYouLookTonight = new Song("S1001", "The Way You Look Tonight", "Micheal Buble", "https://p.scdn.co/mp3-preview/a5b8972e764025020625bbf9c1c2bbb06e394a60?cid=2afe87a64b0042dabf51f37318616965", 4.66, R.drawable.michael_buble_collection);

        Song photograph = new Song("S1002", "Photograph", "Ed Sheeran", "https://p.scdn.co/mp3-preview/097c7b735ceb410943cbd507a6e1dfda272fd8a8?cid=2afe87a64b0042dabf51f37318616965", 4.32, R.drawable.photograph);

        Song beautifulPeople = new Song("S1003", "Beautiful People", "Ed Sheeran", "https://p.scdn.co/mp3-preview/3ad904af9567a7c7df7d23a8d6700296ded34b4f?cid=2afe87a64b0042dabf51f37318616965", 3.30, R.drawable.beautiful_people);

        Song daylight = new Song("S1004", "Daylight", "Joji", "https://p.scdn.co/mp3-preview/698a400b59f139ec6581c8538992bc7f51fec4b3?cid=2afe87a64b0042dabf51f37318616965", 2.73, R.drawable.daylight);

        Song saturdayNights = new Song("S1005", "Saturday Nights", "Khalid", "https://p.scdn.co/mp3-preview/3ddee02c7ce75dd666d24bf81997adebbed8098e?cid=2afe87a64b0042dabf51f37318616965", 3.49, R.drawable.saturday_nights);


        allSongs[0] = theWayYouLookTonight;
        allSongs[1] = photograph;
        allSongs[2] = beautifulPeople;
        allSongs[3] = daylight;
        allSongs[4] = saturdayNights;
    }
    //method to retrieve the index number of the selected song

    public int searchSongById(String id) {
        for (int index = 0; index < allSongs.length; index++) {
            Song tempSong = allSongs[index];
            if (tempSong.getId().equals(id)) {
                return index; //returns the index number if ID is found
            }
        }
        return -1; //return am invalid index number if the ID is not found in array
    }

    //method to retrieve the selected song based on index number
    public Song getCurrentSong(int index) {
        return allSongs[index];
    }

    //create a method to retrieve the index number of the NEXT song
    public int getNextSong(int currentSongIndex) {
        if (currentSongIndex >= allSongs.length - 1) { //if the current song is the last song
            return currentSongIndex;
        } else {
            return currentSongIndex + 1; //go to the index number of the next song
        }
    }

    //create method to retrieve the index number of the PREV song
    public int getPrevSong(int currentSongIndex){
        if(currentSongIndex <= 0){ //check if current song is the first song
            return currentSongIndex;
        }else{
            return currentSongIndex - 1; //go to the index number of the prev song
        }
    }
}
