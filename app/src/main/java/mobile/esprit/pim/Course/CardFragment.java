package mobile.esprit.pim.Course;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import cn.pedant.SweetAlert.SweetAlertDialog;
import mobile.esprit.pim.R;


public class CardFragment extends Fragment {

    private CardView cardView;

    public static Fragment getInstance(int position) {
        CardFragment f = new CardFragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        f.setArguments(args);

        return f;
    }

    @SuppressLint("DefaultLocale")
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.item_viewpager, container, false);

        cardView = (CardView) view.findViewById(R.id.cardView);
        cardView.setMaxCardElevation(cardView.getCardElevation() * CardAdapter.MAX_ELEVATION_FACTOR);

        TextView title = (TextView) view.findViewById(R.id.title);
        TextView textt = (TextView) view.findViewById(R.id.textt);

        Button button = (Button) view.findViewById(R.id.button);
        button.setText("....");
        button.setBackgroundColor(getResources().getColor(R.color.default_light_vibrant));


        if (getArguments().getInt("position") == 0) {
            title.setText(" Quand et comment utiliser l'enfumoir ?");
            textt.setText("Découvrez les techniques " +
                    " pour se protéger ");

        }
        if (getArguments().getInt("position") == 1) {
            title.setText("Comment choisir son extracteur ? ");
            textt.setText("Découvrez nos conseils pour choisir votre extracteur à miel");
        }
        if (getArguments().getInt("position") == 2) {
            title.setText("Comment capturer un essaim ?");
            textt.setText("Découvrez nos techniques pour capturer un essaim sauvage");

        }
        if (getArguments().getInt("position") == 3) {
            title.setText("Comment conserver le miel ?");
            textt.setText("Découvrez nos techniques pour conserver le miel dans ce tutoriel!");

        }
        if (getArguments().getInt("position") == 4) {
            title.setText("Comment nourrir les abeilles ?");
            textt.setText("Découvrez nos conseils pour nourrir correctement les abeille");

        }
        if (getArguments().getInt("position") == 5) {
            title.setText("Comment se débarrasser de parasite ?");
            textt.setText("Découvrez nos méthodes  pour se débarasser de Varroa");

        }
        if (getArguments().getInt("position") == 6) {
            title.setText("Comment se vêtir pour visiter ses ruches ?");
            textt.setText("Découvrez les vêtements indispensables");

        }
        if (getArguments().getInt("position") == 7) {
            title.setText("Comment marquer les reines ?");
            textt.setText("Découvrez nos techniques pour identifier et marquer les reines");

        }

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (getArguments().getInt("position") == 0) {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Comment utiliser un enfumoir ?")
                            .setContentText("\n" +
                                    "\n" +

                                    "Ces deux enfumages devraient normalement suffire pour vous permettre de récolter tranquillement votre miel. Evitez d’injecter trop d’air dans l’enfumoir. Une fumée chaude peut avoir l’effet inverse en rendant les abeilles plus agressives et risque surtout de détériorer le contenu de la ruche notamment la cire et le miel. Durant toute l’opération, adoptez une attitude calme et décontractée pour manier les cadres de ruche. Evitez les mouvements brusques qui pourraient donner une raison aux abeilles de devenir agressives et portez une combinaison intégrale.")
                            .show();


                }
                if (getArguments().getInt("position") == 1) {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Radiaire vs tangentiel")
                            .setContentText("A la question s’il faut un extracteur radiaire ou tangentiel, les réponses divergent selon l’expérience. Pour bien faire le choix, il est intéressant de voir chaque catégorie sous divers angles. Tout d’abord, l’extracteur radiaire peut être soit manuel, soit électrique. Cette machine maintient les cadres positionnés en rayons de cercles dans sa cuve.\n" +
                                    "\n" +
                                    "Elle présente l’avantage de pouvoir contenir une centaine de cadres. C’est le choix de référence lorsqu’on extrait un miel déjà connu pour sa fluidité car on y gagne ainsi en rapidité. Ainsi, un extracteur tangentiel sera plus adapté à un miel plus lourd et épais. Cette machine est l’apanage des apiculteurs amateurs qui disposent encore de peu de ruches. Un peu contraignante dans sa manipulation, également un peu lente, l’extracteur tangentiel nécessite qu’on vide les cellules et qu’on retourne les cadres entre chaque extraction.")
                            .show();
                }
                if (getArguments().getInt("position") == 2) {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Les pièges d’essaims")
                            .setContentText("Généralement, les apiculteurs utilisent différents types de pièges.\n" +
                                    "Des ruches : neuves ou usagées (mais en bon état). Elles doivent bien sentir la propolis. Alors, si l’odeur n’est pas assez forte, n’hésitez pas à frotter les parois et le fond de vos ruches avec des boules de propolis, surtout quand elles sont neuves, afin recouvrir l’odeur du bois. Une vaporisation à l’eau de cire (ou miellée) est un excellent atout pour votre piège.\n" +
                                    "Des produits de la ruche, utilisés séparément ou mieux encore combinés : propolis, eau de cire et miel (ou eau miellée). Trois produits dont l’odeur particulière attire bien les abeilles.\n" +
                                    "Des plantes attire-essaims : vous pouvez vaporiser vos ruches avec l’essence de mélisse, mélinet, verveine, citronnelle, anis (macéré dans l’alcool) ")
                            .show();
                }

                if (getArguments().getInt("position") == 3) {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Quelques précautions")
                            .setContentText(" Il est possible de simplement filtrer le miel et le garder au frais, " +
                                    "loin de l’humidité. Déjà, au moment de la récolte, il est inadapté de le prendre alors qu’il" +
                                    " contient encore trop d’eau. Une autre pratique courante consiste à pasteuriser le miel humide, c’est-à-dire le chauffer à 77°C pour que sa cristallisation ne se produise jamais. Cette méthode aide à conserver le miel mais elle n’épargne pas les vitamines et les minéraux. Elle modifie également la couleur du miel en le rendant plus brun et lui donnant un goût légèrement modifié.\n ")
                            .show();
                }


                if (getArguments().getInt("position") == 4) {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Les types de nourrissement")
                            .setContentText(" Premièrement, le nourrissement spéculatif appelé « biberonnage » ou encore « nourrissement collectif » qui se fait au printemps et qui sert à stimuler la ponte de la reine. Par ce type de nourrissement, on simule le nectar à l’aide d’un sirop de saccharose en petites quantités préparé avec 1kg de sucre et 1L d’eau chaude. Ce sirop de biberonnage sera idéalement " +
                                    "posé en bas de la ruche pour s’écouler lentement par un distributeur qui traverse le trou d’envol.En second lieu, il y a le nourrissement de complément programmé au début de l’automne ou effectué en urgence en hiver. Le produit utilisé serait ici idéalement le miel mais comme la rentabilité de la culture est aussi prise en compte, un produit pouvant lui être assimilé fera l’affaire : un produit pâteux comme le sirop de sucre inverti. Procédez systématiquement à un nourrissement en automne après qu’une partie du miel ait été collecté.\n ")
                            .show();
                }
                if (getArguments().getInt("position") == 5) {
                    new SweetAlertDialog(getContext())
                            .setTitleText("lutter contre les varroas")
                            .setContentText("Ces méthodes consistent à attirer les acariens sur un seul cadre de la ruche, de retirer le cadre en question" +
                                    " et de le détruire. Pour cela, les apiculteurs mettent en place un cadre avec des cellules de faux-bourdons" +
                                    ", le choix de prédilection des femelles varroas pour la ponte.\n" +
                                    "\n" +
                                    "Lorsque les cellules sont operculées, l’apiculteur retirera le cadre et le détruira." +
                                    " Pour attirer les acariens sur un cadre en particulier, il est possible d’utiliser un attractif " +
                                    "à vaporiser sur un cadre non-operculé. Mis au point en laboratoire, ce produit n’est cependant pas facile à utiliser." +
                                    " Selon une étude russe, la destruction du premier cadre à la reprise de la ponte permet d’enlever une grande partie" +
                                    " des varroas présents. L’utilisation de la chaleur contre les varroas est également une piste exploitée à travers" +
                                    " le monde. Comme les acariens ne résistent pas à la chaleur, le défi consiste à trouver la bonne température" +
                                    " pour éliminer les varroas sans pour autant tuer les abeilles. ")
                            .show();
                }
                if (getArguments().getInt("position") == 6) {
                    new SweetAlertDialog(getContext())
                            .setTitleText("Les accessoires de protection")
                            .setContentText("Si auparavant, dans les temps coloniaux, les apiculteurs portaient des casques ou des chapeaux et un voile pour se protéger le visage, la protection de la tête passe aujourd’hui par un casque assez typique. Mais beaucoup d’apiculteurs avérés apprécient toujours le style le plus simple : chapeau avec voilette.\n" +
                                    "\n" +
                                    "En fait, c’est souvent en saison chaude que se font les va-et-vient au rucher. La combinaison intégrale ne serait alors que précurseur de malaises dus à la chaleur. Là, le vêtement léger est de mise. Le voile peut être métallique mais avec des mailles assez serrées pour prévenir les piqures au visage. Quant aux gants, s’ils sont en toile munie d’élastiques, ils peuvent être lavés. Ils garderont leur souplesse et seront réutilisables pendant plusieurs saisons. Les jointures au niveau des articulations doivent être intactes car les abeilles semblent s’acharner à trouver la faille de votre protection lorsqu’elles sont énervées et une seule ouvrière qui s’est frayée un chemin dans un pantalon peut causer de graves problèmes.  ")
                            .show();
                }


                if (getArguments().getInt("position") == 7) {

                    new SweetAlertDialog(getContext())
                            .setTitleText("Marquer la reine")

                            .setContentText("Le marquage de la reine peut se faire dans un véhicule (vitres fermées) ou dans un endroit ombragé, un peu loin de la ruche. Cette opération varie selon le moyen utilisé pour attraper la reine. Avec une cage à reine, le marquage se fait à travers la maille. Mais, vu la vivacité de la reine, si le premier essai est manqué, il est difficile d’y remédier, sans oublier que vous risquez de blesser votre reine.\n" +
                                    "Marquez un point sur le thorax de la reine avec l’instrument choisi pour le marquage. Laissez la marque sécher avant de remettre la reine dans la ruche. La couleur du marquage (blanc, bleu, jaune, rouge et vert) indique l’année de sa naissance.\n" +
                                    "Stressée par le marquage, une reine peut tomber en syncope. N’ayez crainte, elle ne tardera pas à se réveiller.\n" +

                                    "Les ouvrières pourraient se montrer agressives vis-à-vis d’une reine nouvellement marquée. Retirez-la, enfumez les ouvrières, ensuite remettez-la en place.").show();
                }


            }
        });

        return view;
    }

    public CardView getCardView() {
        return cardView;
    }
}
