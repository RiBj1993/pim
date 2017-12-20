package mobile.esprit.pim.timeline;

import java.util.Arrays;
import java.util.List;

import mobile.esprit.pim.R;


public class DataImportUtils {

    public static List<DataInfo> init() {
        DataInfo r1 = new DataInfo();
        r1.cover = R.mipmap.a;
        r1.logo = R.mipmap.abb;
        r1.title = "Janvier ";
        r1.subTitle = "  ";

        DataInfo r2 = new DataInfo();
        r2.cover = R.mipmap.b;
        r2.logo = R.mipmap.abb;
        r2.title = "Février ";
        r2.subTitle = "Surveillance  des abeilles ";
        DataInfo r3 = new DataInfo();
        r3.cover = R.mipmap.c;
        r3.logo = R.mipmap.abb;
        r3.title = "Mars";
        r3.subTitle = "Pollinisation des vergers";

        DataInfo r4 = new DataInfo();
        r4.cover = R.mipmap.d;
        r4.logo = R.mipmap.abb;
        r4.title = "Avril    ";
        r4.subTitle = "  ";

        DataInfo r5 = new DataInfo();
        r5.cover = R.mipmap.e;
        r5.logo = R.mipmap.abb;
        r5.title = "Mai                             .";
        r5.subTitle = "Exposition des ruches a l'acacia";

        DataInfo r6 = new DataInfo();
        r6.cover = R.mipmap.f;
        r6.logo = R.mipmap.abb;
        r6.title = "Juin";
        r6.subTitle = "Récolte du miel d'acacia";

        DataInfo r7 = new DataInfo();
        r7.cover = R.mipmap.g;
        r7.logo = R.mipmap.abb;
        r7.title = " 1-->15 juin                                     . ";
        r7.subTitle = "Exposition des ruches au châtaigniers,tilleuls";
        DataInfo r8 = new DataInfo();
        r8.cover = R.mipmap.h;
        r8.logo = R.mipmap.abb;
        r8.title = "Demi-Juillet";
        r8.subTitle = "2eme récolte du  miel ";

        DataInfo r9 = new DataInfo();
        r9.cover = R.mipmap.h;
        r9.logo = R.mipmap.abb;
        r9.title = "Septempbre";
        r9.subTitle = "Surveillence des abeilles";


        DataInfo r10 = new DataInfo();
        r10.cover = R.mipmap.h;
        r10.logo = R.mipmap.abb;
        r10.title = "Novembre";
        r10.subTitle = "hibernage des abeilles";


        DataInfo r11 = new DataInfo();
        r11.cover = R.mipmap.h;
        r11.logo = R.mipmap.abb;
        r11.title = "Décembre";
        r11.subTitle = "hibernage des abeilles";

        return Arrays.asList(new DataInfo[]{r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11});
    }
}
