package ru.nsu.ccfit.melnikov.minesweeper.model.score;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.nsu.ccfit.melnikov.minesweeper.controller.DifficultyLevel;

import java.io.*;

public class ScoreHandler {
    private static final String PATH = "./lab2/scores/scores.json";

    private static void initScores(Gson gson, File scoresFile) {
        try {
            scoresFile.createNewFile();
            Score[] scores = new Score[]{new Score(DifficultyLevel.NONE, Integer.MAX_VALUE),
                    new Score(DifficultyLevel.EASY, Integer.MAX_VALUE),
                    new Score(DifficultyLevel.MEDIUM, Integer.MAX_VALUE),
                    new Score(DifficultyLevel.EXPERT, Integer.MAX_VALUE)};
            try (FileWriter writer = new FileWriter(scoresFile)) {
                writer.write(gson.toJson(scores));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static Score[] readScoresFromFile(Gson gson, File scoresFile) {
        Score[] scores;
        try (Reader reader = new FileReader(scoresFile)) {
            scores = gson.fromJson(reader, Score[].class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return scores;
    }

    private static void updateScores(Gson gson, File scoresFile, Score score) {
        var scores = readScoresFromFile(gson, scoresFile);
        try (FileWriter writer = new FileWriter(scoresFile)) {
            if (score.getTime() < scores[score.getLevel().ordinal()].getTime()) {
                scores[score.getLevel().ordinal()] = score;
            }
            String scoreJson = gson.toJson(scores);
            writer.write(scoreJson);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void saveScore(Score score) {
        File scoresFile = new File(PATH);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        if (scoresFile.exists()) {
            updateScores(gson, scoresFile, score);
        } else {
            initScores(gson, scoresFile);
            updateScores(gson, scoresFile, score);
        }
    }

    public static int getHighScoreByLevel(DifficultyLevel level) {
        File scoresFile = new File(PATH);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        var scores = readScoresFromFile(gson, scoresFile);
        return scores[level.ordinal()].getTime();
    }
}
