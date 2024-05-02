public class PencariKata {
    private char[][] papan;
    private boolean[][] papanTandai;
    private int baris;
    private int kolom;

    public PencariKata(char[][] papan) {
        this.baris = papan.length;
        this.kolom = papan[0].length;
        this.papan = papan;
        this.papanTandai = new boolean[baris][kolom];
    }

    public void temukanDanTandaiKata(String[] daftarKata) {
        for (String kata : daftarKata) {
            resetPapanTandai();
            if (temukanKata(kata)) {
                System.out.println("Menandai dan mencetak: " + kata);
                cetakPapanTandai();
            } else {
                System.out.println("Kata '" + kata + "' tidak ditemukan.");
            }
            System.out.println();
        }
    }
    

    private boolean temukanKata(String kata) {
        int[] dx = {-1, 1, 0, 0, -1, -1, 1, 1};
        int[] dy = {0, 0, -1, 1, -1, 1, -1, 1};

        for (int baris = 0; baris < this.baris; baris++) {
            for (int kolom = 0; kolom < this.kolom; kolom++) {
                if (papan[baris][kolom] == kata.charAt(0)) {
                    for (int d = 0; d < dx.length; d++) {
                        int x = baris, y = kolom, indeks = 0;
                        while (indeks < kata.length() && x >= 0 && x < this.baris && y >= 0 && y < this.kolom && papan[x][y] == kata.charAt(indeks)) {
                            x += dx[d];
                            y += dy[d];
                            indeks++;
                        }
                        if (indeks == kata.length()) {
                            tandaiKata(baris, kolom, dx[d], dy[d], kata.length());
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    private void tandaiKata(int baris, int kolom, int dx, int dy, int panjang) {
        for (int i = 0; i < panjang; i++) {
            papanTandai[baris][kolom] = true;
            baris += dx;
            kolom += dy;
        }
    }

    private void resetPapanTandai() {
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                papanTandai[i][j] = false;
            }
        }
    }

    public void cetakPapanTandai() {
        for (int i = 0; i < baris; i++) {
            for (int j = 0; j < kolom; j++) {
                if (papanTandai[i][j]) {
                    System.out.print(papan[i][j] + " ");
                } else {
                    System.out.print("- ");
                }
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char[][] papan = {
            {'J', 'S', 'O', 'L', 'U', 'T', 'I', 'S'},
            {'S', 'U', 'N', 'A', 'R', 'U', 'U', 'A'},
            {'N', 'E', 'P', 'T', 'U', 'N', 'E', 'T'},
            {'S', 'O', 'N', 'I', 'E', 'I', 'S', 'U'},
            {'R', 'C', 'E', 'V', 'T', 'R', 'E', 'R'},
            {'A', 'H', 'T', 'R', 'A', 'E', 'S', 'N'},
            {'M', 'M', 'E', 'R', 'C', 'U', 'R', 'Y'}
        };

        String[] kata = {"EARTH", "MARS", "MERCURY", "NEPTUNE", "SATURN", "URANUS", "VENUS"};

        PencariKata puzzle = new PencariKata(papan);

        System.out.println("\nHasil pencarian kata satu per satu:");
        puzzle.temukanDanTandaiKata(kata);
    }
}
