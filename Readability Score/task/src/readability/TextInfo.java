package readability;

import java.util.HashSet;
import java.util.Set;

public class TextInfo {
    private final int words;
    private final int sentences;
    private final int characters;
    private final int syllables;
    private final int polysyllables;

    private TextInfo(int words, int sentences, int characters, int syllables, int polysyllables) {
        this.words = words;
        this.sentences = sentences;
        this.characters = characters;
        this.syllables = syllables;
        this.polysyllables = polysyllables;
    }

    public static TextInfo createInfo(String text) {
        String[] sentences = text.toLowerCase().split("(?<=[.!?])");
        int numberOfSentences = sentences.length;
        int characters = 0;
        int numberOfWords = 0;
        int syllables = 0;
        int polysyllables = 0;
        for (int i = 0; i < numberOfSentences; i++) {
            if (sentences[i].isBlank()) {
                numberOfSentences--;
                continue;
            }
            String[] words = sentences[i].trim().split("\\s");
            numberOfWords += words.length;
            for (String word : words) {
                characters += word.length();
                int temp = countSyllables(word);
                if (temp > 2) {
                    polysyllables++;
                }
                syllables += temp;
            }
        }
        return new TextInfo(numberOfWords, numberOfSentences, characters, syllables, polysyllables);
    }

    private static int countSyllables(String word) {
        int syllables = 0;
        boolean isVowel = false;
        Set<Character> vowels = new HashSet<>() {{
            add('a');
            add('o');
            add('i');
            add('e');
            add('u');
            add('y');
        }};
        word = word.replaceAll("[^a-zA-Z]", "");
         for (int i = 0; i < word.length(); i++) {
            if (i == word.length() - 1 && word.charAt(i) == 'e') {
                continue;
            }
            if (vowels.contains(word.charAt(i)) && !isVowel) {
                syllables++;
            }
            isVowel = vowels.contains(word.charAt(i));
        }
        if (syllables == 0) {
            return 1;
        }
        return syllables;
    }


    public int getWords() {
        return words;
    }

    public int getSentences() {
        return sentences;
    }

    public int getCharacters() {
        return characters;
    }

    public int getSyllables() {
        return syllables;
    }

    public int getPolysyllables() {
        return polysyllables;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Words: ")
                .append(words)
                .append("\n")
                .append("Sentences: ")
                .append(sentences)
                .append("\n")
                .append("Characters: ")
                .append(characters)
                .append("\n")
                .append("Syllables: ")
                .append(syllables)
                .append("\n")
                .append("Polysyllables: ")
                .append(polysyllables);
        return stringBuilder.toString();
    }
}
