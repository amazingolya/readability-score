package readability;

public class ReadabilityIndexCalculator {
    public double getAutomatedIndex(TextInfo textInfo) {
        return 4.71 * textInfo.getCharacters() / textInfo.getWords()
                + 0.5 * textInfo.getWords() / textInfo.getSentences() - 21.43;
    }
    public double getFleschKincaidIndex(TextInfo textInfo) {
        return 0.39 * textInfo.getWords() / textInfo.getSentences()
                + 11.8 * textInfo.getSyllables() / textInfo.getWords() - 15.59;
    }
    public double getSMOGIndex(TextInfo textInfo) {
        return 1.043 * Math.sqrt(textInfo.getPolysyllables() * 30.0 / textInfo.getSentences()) + 3.1291;
    }
    public double getColemanLiauIndex(TextInfo textInfo) {
        double l = (double) textInfo.getCharacters() / textInfo.getWords() * 100;
        double s = (double) textInfo.getSentences() / textInfo.getWords() * 100;
        return 0.0588 * l - 0.296 * s - 15.8 ;
    }
}
