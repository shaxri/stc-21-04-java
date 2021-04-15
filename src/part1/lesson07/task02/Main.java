package part1.lesson07.task02;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Main {
    private final static Random rand = new Random();
    private final static int COMMA_PROBABILITY = 8;
    private final static char[] sentenceEndings = {'.', '!', '?'};
    private final static int PARAGRAPH_NUMBER = 19;

    public static void main(String[] args) {
        String[] words = {"aggrievedly", "ellfish", "agileness", "graphotypic", "graphostatical"};
        int n = 3;
        int size = 2000;
        int probability = 2;

        String path = "./src/part1/lesson07/task02/";
        getFiles(path, n, size, words, probability);

    }

    private static void getFiles(String path, int n, int size, String[] words, int probability) {
        String[] fileNames = generateRandomWords(n);
        for (String fileName : fileNames) {

            try (FileOutputStream fos = new FileOutputStream(path+fileName+".txt");
                 BufferedOutputStream bos = new BufferedOutputStream(fos)) {
                StringBuffer paragraphs = insertParagraphs(generateSentences(size, words, probability));
                bos.write(paragraphs.toString().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    private static StringBuffer insertParagraphs(StringBuffer sentences) {
        char[] paragraphChars = {'\n', '\n', '\t'};
        List<String> separateSentences = new ArrayList<>(Arrays.asList(sentences.toString().split("(?<=[.!?] )")));
        sentences.delete(0, sentences.length());
        int offset = 0;
        while (offset < separateSentences.size()) {
            int randomParagraphNumbers = rand.nextInt(PARAGRAPH_NUMBER) + 1;

            if (offset + randomParagraphNumbers > separateSentences.size()) {
                break;
            }
            if (offset == 0) {
                sentences.append(paragraphChars[2]);
            }
            List<String> paragraph = separateSentences.subList(offset, offset + randomParagraphNumbers);
            for (String sentence : paragraph) {
                sentences.append(sentence);
            }
            sentences.deleteCharAt(sentences.lastIndexOf(" "));
            sentences.append(paragraphChars);
            offset += randomParagraphNumbers;
        }

        for (String sentence : separateSentences.subList(offset, separateSentences.size())) {
            sentences.append(sentence);
        }


        return sentences;
    }

    public static StringBuffer generateSentences(int size, String[] words, int probability) {


        StringBuffer sb = new StringBuffer();
        String[] generatedWords;
        while (sb.length() < (size)) {
            int difference = size - sb.length();
            //forming a sentence
            if (difference > 225) {
                generatedWords = generateRandomWords(rand.nextInt(14) + 1);
            } else {
                int numberOfWords = difference > 15 ? difference / 15 : 1;
                generatedWords = generateRandomWords(numberOfWords);
            }

            //put some word from selected words with probability
            if (rand.nextInt(probability) == probability - 1) {
                generatedWords[rand.nextInt(generatedWords.length)] = words[rand.nextInt(words.length)];
            }
            for (int i = 0; i < generatedWords.length; i++) {

                //capitalize first word in a sentence
                if (i == 0) {
                    generatedWords[i] = capitalize(generatedWords[i]);
                }
                if (i == generatedWords.length - 1) {
                    sb.append(generatedWords[i]).append(sentenceEndings[rand.nextInt(3)]).append(" ");
                } else {
                    if (rand.nextInt(COMMA_PROBABILITY) == COMMA_PROBABILITY - 1) {
                        sb.append(generatedWords[i]).append(", ");
                    } else {
                        sb.append(generatedWords[i]).append(" ");
                    }
                }

            }
            //end of sentence
        }

        return sb;
    }

    private static String[] generateRandomWords(int numberOfWords) {
        String[] randomStrings = new String[numberOfWords];
        Random random = new Random();
        for (int i = 0; i < numberOfWords; i++) {
            char[] word = new char[random.nextInt(14) + 1]; // words of length 3 through 10. (1 and 2 letter words are boring.)
            for (int j = 0; j < word.length; j++) {
                word[j] = (char) ('a' + random.nextInt(26));
            }
            randomStrings[i] = new String(word);
        }
        return randomStrings;
    }

    private static String capitalize(String str) {
        if (str == null) return null;
        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }
}
