import java.io.File;
import java.io.PrintWriter;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner input = new Scanner(new File(".\\input.txt"));
        PrintWriter writer = new PrintWriter(".\\output.txt");

        int n = input.nextInt();

        for (int i = 0; i < n; i++) {
            int type = input.nextInt();
            String sequence = input.next().trim();
            int k = input.nextInt();
            int w = input.nextInt();

            if (type == 1) {
                Minimizer miniSeeds = new Minimizer(sequence, k, w);

                writer.printf("For sequence: \"%s\", k: %d, and w: %d\n", sequence, k, w);
                writer.printf("k-mers:\n-\t%s:\n%s\n", sequence, miniSeeds.getKMersString());
                writer.printf("Minimizer seeds:\n%s\n", miniSeeds.getSeedsString());
                writer.printf("Hash Table:\n%s\n", miniSeeds.getHashTableString());
            } else if (type == 2) {
                String p = input.next();

                GenomeOnDiet seeds = new GenomeOnDiet(sequence, k, w, p);

                writer.printf("For sequence: \"%s\", k: %d, w: %d, and p: %s\n", sequence, k, w, p);
                writer.printf(
                        "k-mers:\n-\t%s:\n-\t%s:\n-\t%s:\n%s\n",
                        sequence,
                        seeds.getPatternedSequenceString(),
                        seeds.getPatternedSequence(),
                        seeds.getKMersString()
                );
                writer.printf("Genome-on-Diet seeds:\n%s\n", seeds.getSeedsString());
                writer.printf("Hash Table:\n%s\n", seeds.getHashTableString());
            }
            writer.println("#".repeat(120));
        }

        input.close();
        writer.close();
    }

}
