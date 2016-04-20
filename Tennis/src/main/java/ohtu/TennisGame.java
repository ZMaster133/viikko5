package ohtu;

public class TennisGame {

    private int player1Score = 0;
    private int player2Score = 0;
    private final String player1Name;
    private final String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else if (playerName.equals(player2Name)) {
            player2Score += 1;
        } else {
            throw new IllegalArgumentException("Tuntematon pelaaja");
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return formatEqualScore(player1Score);
        } else if (player1Score >= 4 || player2Score >= 4) {
            return formatScoreOver3(player1Score, player2Score);
        } else {
            return formatScoreLessThan4(player1Score, player2Score);
        }
    }

    private String formatEqualScore(int score) {
        if (score >= 4) {
            return "Deuce";
        } else {
            return convertScoreToString(score) + "-All";
        }
    }

    private String formatScoreOver3(int score1, int score2) {
        int difference = score1 - score2;
        
        if (difference == 1) {
            return "Advantage player1";
        } else if (difference == -1) {
            return "Advantage player2";
        } else if (difference >= 2) {
            return "Win for player1";
        } else {
            return "Win for player2";
        }
    }

    private String formatScoreLessThan4(int score1, int score2) {
        return convertScoreToString(score1) + "-" + convertScoreToString(score2);
    }

    private String convertScoreToString(int score) {
        switch (score) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
        }

        return "";
    }
}
