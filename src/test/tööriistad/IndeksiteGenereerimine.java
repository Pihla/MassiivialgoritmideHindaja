package tööriistad;

import main.MassiiviTööriistad;

import java.util.ArrayList;
import java.util.List;

public class IndeksiteGenereerimine {

    public static List<MassiiviTööriistad.TööalaIndeksid> leiaKõikvõimalikudTööalaValimiseIndeksid(int massiiviPikkus) {
        List<MassiiviTööriistad.TööalaIndeksid> indeksitePaarid = new ArrayList<>();

        //Tööala valimised eeldusega, et algusindeks < lõpuindeks
        for (int i = 0; i < massiiviPikkus; i++) {
            for (int j = i; j < massiiviPikkus; j++) {
                indeksitePaarid.add(new MassiiviTööriistad.TööalaIndeksid(i, j+1));
            }
        }

        return indeksitePaarid;
    }

    public static List<MassiiviTööriistad.PisteIndeksid> leiaKõikVõimalikudPisteIndeksid(int massiiviPikkus) {
        List<MassiiviTööriistad.PisteIndeksid> indeksitePaarid = new ArrayList<>();

        //Pisted eeldusega, et piste alg- ja lõpp-punkt on erinevad
        for (int i = 0; i < massiiviPikkus; i++) {
            for (int j = 0; j < massiiviPikkus; j++) {
                if(i != j) {
                    indeksitePaarid.add(new MassiiviTööriistad.PisteIndeksid(i, j));
                }
            }
        }

        return indeksitePaarid;
    }

    public static List<MassiiviTööriistad.VahetatavadIndeksid> leiaKõikVõimalikudVahetusteIndeksid(int massiiviPikkus) {
        List<MassiiviTööriistad.VahetatavadIndeksid> indeksitePaarid = new ArrayList<>();

        //Elementide vahetused eeldusega, et vahetatavad elemendid on erinevad.
        // Elementide järjekorral vahetuses pole vahet, nii et võtan ainult juhud, kus esimene on väiksem
        for (int i = 0; i < massiiviPikkus; i++) {
            for (int j = i+1; j < massiiviPikkus; j++) {
                indeksitePaarid.add(new MassiiviTööriistad.VahetatavadIndeksid(i, j));
            }
        }

        return indeksitePaarid;
    }


}
