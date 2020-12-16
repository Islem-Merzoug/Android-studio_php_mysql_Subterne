package solution.citec.oxo.i.com.citecsolution.Tools;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.View;

import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

import solution.citec.oxo.i.com.citecsolution.AboutActivity;
import solution.citec.oxo.i.com.citecsolution.HistoryShartActivity;
import solution.citec.oxo.i.com.citecsolution.HistoryTableActivity;
import solution.citec.oxo.i.com.citecsolution.LoginActivity;
import solution.citec.oxo.i.com.citecsolution.R;
import solution.citec.oxo.i.com.citecsolution.helper.SessionManager;

import static androidx.appcompat.content.res.AppCompatResources.getDrawable;


public class DrawerUtil {




    public static Drawer getDrawer(final Activity activity) {






        //if you want to update the items at a later time it is recommended to keep it in a variable
        PrimaryDrawerItem commandes = new PrimaryDrawerItem().withIdentifier( 1 ).withName( "3 Months Chart " ).withSelectedTextColorRes( R.color.colorAccent );
        PrimaryDrawerItem panier = new PrimaryDrawerItem().withIdentifier( 2 ).withName( "Consumption History" ).withSelectedTextColorRes( R.color.colorAccent );
        PrimaryDrawerItem parametres = new PrimaryDrawerItem().withIdentifier( 3 ).withName( "About" ).withSelectedTextColorRes( R.color.colorAccent );
        PrimaryDrawerItem aide = new PrimaryDrawerItem().withIdentifier(4).withName( "Log out" ).withSelectedTextColorRes( R.color.colorAccent );

        commandes.withIcon( getDrawable( activity, R.drawable.ic_launcher_background ) );
        panier.withIcon( getDrawable( activity, R.drawable.ic_launcher_background ) );
        parametres.withIcon( getDrawable( activity, R.drawable.ic_launcher_background ) );
        aide.withIcon( getDrawable( activity, R.drawable.ic_launcher_background ) );


//create the drawer and remember the `Drawerr` result object
        final Drawer result = new DrawerBuilder()
                .withActivity( activity )
                //ajouter
                .withHeader( R.layout.header_drawer_layout )
                .withActionBarDrawerToggle( true )
                .withTranslucentStatusBar( false )
                // jusqu a ici

                .addDrawerItems(
                        commandes,
                        panier,
                        parametres,
                        aide
                ).withSelectedItem( -1 )
                .withOnDrawerItemClickListener(
                        new Drawer.OnDrawerItemClickListener() {
                            @Override
                            public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                                // do something with the clicked item :D


                                if (drawerItem.getIdentifier() == 1) {

                                    view.getContext().startActivity( new Intent( activity, HistoryShartActivity.class ) );
                                    activity.overridePendingTransition(0, 0);


                                }
                                if (drawerItem.getIdentifier() == 2) {
                                    view.getContext().startActivity( new Intent( activity, HistoryTableActivity.class ) );
                                    activity.overridePendingTransition(0, 0);


                                }

                                if (drawerItem.getIdentifier() == 3) {

                                    view.getContext().startActivity( new Intent( activity, AboutActivity.class ) );
                                    activity.overridePendingTransition(0, 0);

                                }
                                if (drawerItem.getIdentifier() == 4) {
                                     ProgressDialog pDialog = new ProgressDialog(activity);
                                     pDialog.setCancelable(false);
                                    pDialog.setMessage("Log out ...");
                                    pDialog.show();
                                    // Session manager
                                    SessionManager session = new SessionManager(activity);
                                    session.setLogin( false );
                                    view.getContext().startActivity(new Intent(activity, LoginActivity.class));
                                    activity.finish();
                                    pDialog.dismiss();
                                }

                                return false;
                            }
                        } )

                .build();

//ajouter


        return result;


    }

    public interface clickOpenDrawer {

        void onClicks();

    }

}
