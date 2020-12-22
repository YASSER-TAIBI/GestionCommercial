/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utils;

/**
 *
 * 
 */
public class Constantes {

    
    
    /*MESSAGE D'ERREUR */
   public static boolean DeveloppingMode=true;
   
   
   public static int LISTE_ARTICLE=0;
   public static int FAMILLE_ARTICLE=1;
   public static int DETAIL_FAMILLE_ARTICLE=2;
   public static int SOUS_FAMILLE_ARTICLE=3;
   public static int DETAIL_SOUS_FAMILLE_ARTICLE=4;
   
   
   public static int LISTE_BANQUE=0;
   public static int LISTE_CHEQUE=1;
   public static int LISTE_CLIENT_PF=2;
   public static int LISTE_DEPOT=3;
   public static int LISTE_CLIENT=4;
   public static int LISTE_VILLE=5;
   public static int LISTE_MAGASIN=6;
   public static int LISTE_SECTEUR=7;
   public static int LISTE_VEHICULE=8;
   public static int LISTE_VENDEUR=9;
   public static int LISTE_CONSULTATION_VENDEUR=10;
   public static int LISTE_CAISSIER=11;
   public static int LISTE_CONSULTATION_CAISSIER=12;
   public static int LISTE_CHAUFFEUR=13;
   public static int LISTE_OFFRE=14;
   public static int LISTE_TYPE_VENTE=15;
   public static int LISTE_COMPTE_VERSEMENT=16;
   public static int LISTE_PRIX_VENTE_DEPOT=17;
   
   
   public static int LISTE_UTILISATEUR=0;
   public static int GERER_AUTHORISATION=1;
   
   
   public static int SITUATION_STOCK=0;
   public static int SITUATION_STOCK_SIEGE=1;
   public static int SITUATION_STOCK_GLOBAL=2;
   public static int INITIAL_STOCK=3;
   public static int RECEPTION_STOCK=4;
   public static int DECLARATION_RECEPTION=5;
   public static int RECEPTION_ETRANGERE=6;
   public static int TRANSFERT_STOCK=7;
   public static int CORRECTION_TRANSFERTS_SORTIE_STOCK=8;
   public static int SORTIE_STOCK=9;
   public static int ETAT_SORTIES=10;
   public static int COMMANDEE_CLIENT=11;
   public static int VERSEMENT_CLIENT=12;
   public static int VERSEMENT_CHEQUE_CLIENT=13;
   public static int VERSEMENT_BANCAIRE_CLIENT=14;
   public static int CONSULTATION_COMPTE_CLIENT=15;
   public static int AVANCE_CHAUFFEUUR=16;
   public static int AVANCE_MAGASINIIER=17;
   public static int TRANSFERT_EN_ATTENTE=18;
   public static int RECEPTION_EN_ATTENTE=19;
   public static int RETOUR_DEFINITIFS=20;

   
   
   public static int CREATION_TOURNEE=0;
   public static int SITUATION_INITIAL=1;
   public static int SITUATION_TOURNEE=2;
   
   
   public static int CHARGEMENTS=0;
   public static int RETOURS=1;
   public static int SUIVI_VENTES=2;
   public static int SAISIE_VERSEMENT=3;
   public static int VERSEMENT_BANCAIRE=4;
   public static int VERSEMENT_CHEQUE=5;
   public static int LISTE_VERSEMENT_CHEQUE=6;
   public static int LISTE_VERSEMENT_BANCAIRE=7;
   public static int SAISIE_FACTURE=8;
   public static int SITUATION_VENTE_FACTURE=9;
   public static int ETAT_VERSEMENTCHEQUE_CAISSIER=10;
   public static int TRANSFERT_CHEQUES=11;
   public static int CONSULTATION_COMPTE_VENTE=12;
   public static int REPORTS_DE_SOLDES=13;
   
           
   //OBSERVABLE_LISTE
   public static String ARTICLE="Liste Article";
   public static String FAMILLE="Famille Article";
   public static String DETAIL_FAMILLE="Detail Famille Article";
   public static String SOUS_FAMILLE="Sous Famille Article";
   public static String DETAIL_SOUS_FAMILLE="Detail Sous Famille Article";
   
   
   public static String BANQUE="Liste Banque";
   public static String CHEQUE="Liste Cheque";
   public static String CLIENT_PF="Liste ClientPF";
   public static String DEPOT="Liste Depot";
   public static String CLIENT="Liste Client";
   public static String VILLE="Liste Ville";
   public static String MAGASIN="Liste Magasin";
   public static String SECTEUR="Liste Secteur";
   public static String VEHICULE="Liste Vehicule";
   public static String VENDEUR="Liste Vendeur";
   public static String CONSULTATION_VENDEUR="Consultation Vendeur";
   public static String CAISSIER="Liste Caissier";
   public static String CONSULTATION_CAISSIER="Consultation Caissier";
   public static String CHAUFFEUR="Liste Chauffeur";
   public static String OFFREE="Liste Offre";
   public static String TYPE_VENTE="Liste Type Vente";
   public static String COMPTE_VERSEMENT="Liste Compte Versement";
   public static String PRIX_VENTE_DEPOT="Liste Prix Vente Depot";
   
   
   public static String UTILISATEUR="Liste Utilisateur";
   public static String AUTHORISATION="Gérer Authorisation";


   public static String SITUATION="Situation Stock";
   public static String SITUATION_SIEGE="Situation Stock Siege";
   public static String SITUATION_GLOBAL="Situation Stock Global";
   public static String INITIAL="Initial Stock";
   public static String RECEPTIONS="Réception Stock";
   public static String DECLARATION_RECEPTIONS="Déclaration Réception";
   public static String RECEPTIONS_ETRANGERE="Réception Etrangère";
   public static String TRANSFERTS="Transfert Stock";
   public static String CORRECTION_TRANSFERTS_SORTIE="Correction Transfert Sortie";
   public static String SORTIE="Sortie Stock";
   public static String ETAT_SORTIE="Etat Sortie";
   public static String COMMANDE_CLIENT="Commande Client";
   public static String VERSEMENT_CLIENTS="Paiement Avance Client";
   public static String VERSEMENT_CHEQUE_CLIENTS="Versement Chéque Client";
   public static String VERSEMENT_BANCAIRE_CLIENTS="Versement Bancaire Client";
   public static String CONSULT_COMPTE_CLIENT= "Consultation Compte Client";
   public static String AVANCE_CHAUFFEUR= "Avance Chauffeur";
   public static String AVANCE_MAGASINIER= "Avance Magasinier";
   public static String TRANSFERT_EN_ATTENTEE="Transfert En Attente";
   public static String RECEPTION_EN_ATTENTEE="Réception En Attente";
   public static String RETOUR_DEFINITIF="Retour Définitif";
   
   public static String TOURNEE="Création Tournée";
   public static String SITIAT_INITIAL="Situation Initial";  
   public static String SITUAT_TOURNEE="Situation Tournée";
   
   
   public static String CHARGEMEENT="Chargement";
   public static String RETOUUR="Retour";
   public static String SUIVI_VENTEES="Suivi Ventes";
   public static String VERSEMENT="Saisie Versement";
   public static String VERSEMENT_BANCAIIRE="Versement Bancaire";
   public static String VERSEMENT_CHEQUEE="Versement Cheque";
   public static String LISTE_VERSEMENT_CHEQUEE="Liste Versement Cheque";
   public static String LISTE_VERSEMENT_BANCAIIRE="Liste Versement Bancaire";
   public static String FACTURES="Saisie Facture";
   public static String SITUATION_VENTEFACTURE="Situation Vente/Facture";  
   public static String ETAT_VERSEMENT_CHEQUE_CAISSIER="Etat Versement Cheque Caissier"; 
   public static String TRANS_CHEQUE="Transfert Chéque";
   public static String CONSULT_COMPTE_VENTE= "Consultation Compte Vente";
   public static String REPO_DE_SOLDES= "Reports De Soldes";
   
//==============================================================================================================================================================================================================================================
   
       public static String ETAT_INITIAL="Initial";   
       public static String ETAT_OUVERT="Ouvert"; 
       public static String ETAT_EN_COURS="En Cours";
       public static String ETAT_VENTE="Vente";
       public static String ETAT_VERSEMENT="Versement";
       public static String ETAT_TRAITE="Traite";
       public static String ETAT_VERSEMENT_CLIENT="Versement Client";
       public static String ETAT_VERSEMENT_CHEQUE="Versement Cheque";
       public static String ETAT_VERSEMENT_BANCAIRE="Versement Bancaire";
       public static String ETAT_VERSEMENT_CHEQUE_RETOUR="V/Cheque Retour";
       public static String ETAT_VERSEMENT_CHEQUE_REMISE="V/Cheque Remise";
       public static String ETAT_FACTURER="Facturer";
       public static String ETAT_SUSPENDU="Suspendu";
       public static String ETAT_REMISE="Remise";
       public static String ETAT_FERMER="Fermer";
       
       String CHAMP_OBLIGATOIRE="Champ Obligatoire";
       public static String CHAMP_VIDE="";
       public static String CHAMP_ETOILE="*";
       public static String CHAMP_TELEPHONE_INVALIDE="Invalide";
       public static String CHAMP_EMAIL_INVALIDE="Email Invalide";
       
       //Remise Speciale
       public static String SANS_REMISE_SP="Sans_RS";
       public static String REMISE_SP="RS";
       //Remise Espece
       public static String SANS_REMISE_ES="Sans_RE";
       public static String REMISE_ES="RE";
       
       
       public static String TYPE_ALERT_INFORMATION="Information";
       public static String TYPE_ALERT_SUCCEE="Succee";
       public static String TYPE_ALERT_ATTENTION="Attention";
       public static String TYPE_ALERT_ERRUR="Erreur";
       
       public static String TYPE_REMISE_COMSTANT="Remise Constant";
       public static String TYPE_REMISE_VARIABLE="Remise Variable";    
       public static String TYPE_REMISE_SANS="Sans Remise";  
       
       public static String PERIODIQUE="Périodique ";    
       public static String OUVERT="Ouvert";  
       
       public static String GROUPE="Groupe";
       public static String INDIVIDUEL="Individuel";
       
       public static String SELECTION_MONTANT = "Tapez le Montant !!";
       public static String MESSAGE_ALERT_AJOUT ="Ajout Avec Succee";
       public static String MESSAGE_ALERT_MODIFICATION="Donnee Modifier avec succee";
       public static String MESSAGE_ALERT_SUPPRIMER="Donnée Supprimer avec succee";
       public static String MESSAGE_ALERT_SELECTIONNER="Veuillez selectionner un enregistrement SVP  !!";
       public static String MESSAGE_ALERT_SELECTIONNER_RECEPTION="Veuillez Sélectionner le Type de Réception SVP !!";
       public static String MESSAGE_ALERT_SELECTIONNER_INITIAL="Veuillez Remplir Tous les champs SVP !!";
       public static String MESSAGE_ALERT_SELECTIONNER_TRANSFERT="Veuillez Sélectionner le Type de Transfert SVP !!";
       public static String MESSAGE_ALERT_SELECTIONNER_TRANSFERT_MODIFIER="Veuillez Sélectionner du Tableau un Transfert a modifier SVP !!";
       public static String MESSAGE_SAISIES_CHAMPS="Veuillez vérifier les données que vous avez saisies, doit être en chiffre !";
       public static String MESSAGE_ALERT_saisir_ville="Veuillez saisir une ville SVP !!";
       public static String MESSAGE_ALERT_saisir_Quantite="Veuillez saisir une quantite inférieur à la quantite total SVP !!";
       public static String MESSAGE_ALERT_TRAITEMENT_MIXTE="Le traitement des articles 'Mixte' sont pas encor codé, veuillez contacte le développeur SVP  !!";
       public static String MESSAGE_ALERT_saisir_secteur ="Veuillez entrer un Secteur SVP !!"; 
       public static String MESSAGE_ALERT_REMPLIR_CHAMPS ="Vous devez Remplir tous les Champs SVP !!";  
       public static String MESSAGE_ALERT_VERIFIER_MONTANT ="Veuillez Vérifier le Montant Total Reçu et Différent du montant Versement Reçu !!";  
       public static String MESSAGE_ALERT_EXISTE ="Erreur Versement, Reçu Bancaire Existe Déjà";
       public static String MESSAGE_ALERT_ERREUR_RETOUR ="Errour Retour !! ";       
       public static String REMPLIR_CHAMPS = "Vous devez Remplir Tous les champs SVP !!";    
       public static String VERIFICATION_SELECTION_LIGNE ="Il faut selectionner une ligne SVP !!";
       public static String SELECTION_LIGNE_MODIFIER ="Il faut seléctionner la ligne à modifier SVP !!";
       public static String SELECTION_DOSSIER ="Il faut sélectionner la ligne du dossier a traité SVP !!";
       public static String SELECTION_ERREUR = "Erreur Seléction";
       public static String ERREUR_SAISIE = "Erreur de Saisie";
       public static String ERREUR ="Erreur";
       public static String MESSAGE_ALERT_CHARGEMENT_VENDEUR= "le Chargement de cette Vendeur a deja été validé ";    
       public static String MESSAGE_ALERT_VERIFIER_COORDONNEES= "Veuillez Vérifier les Coordonnées que vous avez Saisie SVP!! ";    
       public static String MESSAGE_ALERT_VERIFIER_VENDEUR_SECTEUR= "Veuillez Vérifier si vous avez deja créé 'Vendeur ou Secteur' Svp!! ";   
       public static String MESSAGE_ALERT_INITIAL_VENDEUR = "l'initial de ce Vendeur a deja été validé ";   
       public static String MESSAGE_ALERT_SELECTIONNER_CHEQUE = "Sélectionner les Cheques que Vous Pouvez Transférer SVP !!";
       public static String MESSAGE_ALERT_SELECTIONNER_CHEQUE_ETAT = "Sélectionner un Cheque pour changer l'etat SVP !!";
       public static String MESSAGE_ALERT_FAUX_CODE ="Vous devez Vérifier le Code que Vous avez Saisie SVP!!";
       
       public static String MESSAGE_ALERT_OFFRE_EXISTE_PAS = "Vous devez Vérifier l'Offre n'existe pas SVP!!";
       public static String MESSAGE_ALERT_PATIENTER ="Merci de patienter quelques secondes s'il vous plait, afin que le traitement soit termine.";  
       public static String MESSAGE_ALERT_VERIFICATION_OFFRE ="Pourriez-vous s'il vous plaît vérifier que la liste contient des offres."; 
       public static String MESSAGE_ALERT_ENTRER_DATE ="Veuillez entrer la date SVP !! ";     
       public static String MESSAGE_ALERT_SELECTIONNER_VENDEUR ="Veuillez selectionner le vendeur SVP !!";  
       public static String MESSAGE_ALERT_SELECTIONNER_CLIENT ="Veuillez selectionner le client SVP !!"; 
       public static String MESSAGE_ALERT_VERIFICATION_CLIENT ="Veuillez Vérifier le Num Client que Vous avez Saisie SVP!!"; 
       public static String MESSAGE_ALERT_SELECTIONNER_TYPE_VENTE ="Veuillez selectionner le type de vente SVP !!"; 
       public static String MESSAGE_ALERT_SELECTIONNER_TYPE_REMISE ="Veuillez selectionner le type de Remise SVP !!";
       public static String MESSAGE_ALERT_SELECTIONNER_DEPOT ="Veuillez selectionner le depot SVP !!"; 
       public static String MESSAGE_ALERT_SELECTIONNER_MAGASIN ="Veuillez selectionner le magasin SVP !!"; 
       public static String MESSAGE_ALERT_SELECTIONNER_ARTICLE ="Veuillez selectionner l'Article SVP !!"; 
       public static String MESSAGE_ALERT_ENTRER_CODE_ARTICLE ="Veuillez saisir Code Article SVP !!"; 
       public static String MESSAGE_ALERT_ENTRER_CODE_FACTURE ="Veuillez saisir Code Facture SVP !!"; 
       public static String MESSAGE_ALERT_ENTRER_QUANTITE ="Veuillez saisir la quantite SVP !!"; 
       public static String MESSAGE_ALERT_ENTRER_REMISE ="Veuillez saisir la Remise SVP !!"; 
       public static String MESSAGE_ALERT_SELECTIONNER_TOURNEE ="Veuillez selectionner la tournee SVP !!"; 
       public static String MESSAGE_ALERT_SELECTIONNER_TABLE ="Veuillez Vérifier si la Table n'est pas Vide avant d'éxcuter la Facture SVP !!"; 
       public static String MESSAGE_ALERT_VERIFICATION_TOURNEE ="Veuillez Vérifier la tournee n'existe pas!!"; 
       public static String MESSAGE_ALERT_FACTURE_TOURNEE ="Tournée facturer avec succée SVP !!"; 
       public static String MESSAGE_ALERT_TOURNEE_AUCUNE_FACTURE ="Tournée n'a aucune facture SVP !!"; 
       public static String MESSAGE_ALERT_FAUX_DATE = "Vous devez Vérifier la Date que Vous avez Saisie SVP!!";
       public static String MESSAGE_ALERT_TOURNEE_OUVERTE = "Vous devez Vérifier la tournee est deja Ouverte SVP!!";
       public static String MESSAGE_ALERT_FAUX_DATE_VIDE = "Vous devez Vérifier la Date n'existe pas SVP!!";
       public static String MESSAGE_ALERT_DATE = "Vous devez Vérifier la Date Transfert Sortie SVP!!";
       public static String MESSAGE_ALERT_ATTENTION_EN_COURS_TRAITEMENT = "Vous n'avez pas le droit de Créer une nouvelle tournée, le traitement n'a pas terminer";
       public static String MESSAGE_ALERT_CREER_PRIX ="Vous devez Créer le Prix de cet Article d'abord!!";
       public static String MESSAGE_ALERT_DATE_FIN_SUPPERIEUR_DATE_DEBUT ="Vous devez Vérifier la Date Fin et Supérieur du Date Debut SVP!!";
       public static String MESSAGE_ALERT_CHARGE_SUPP ="La Charge Supplémentaire bien Ajouté";
       public static String MESSAGE_ALERT_DOSSIER_EXISTE ="Un dossier est déjà au traitement, vous n'avez pas le droit d'ajouter un autre";
       
       
       public static String MESSAGE_ALERT_VALIDER_MODIFICATION="Voullez vous vraiment Modifier l'enregistrement ?";
       public static String MESSAGE_ALERT_VALIDER_SUPPRIMER="Voullez vous vraiment supprimer l'enregistrement ?";
       public static String MESSAGE_ALERT_QUITTER_APP ="Voulez-vous vraiment quitter l'application ?";
       public static String MESSAGE_ALERT_CONTINUER ="Voulez-vous vraiment continuer ce processus ?";
       public static String MESSAGE_ALERT_FERMER_SESSION ="Voulez-vous vraiment fermer la session ?";
       
       public static String VERSEMENT_CLIENT_ESPECE_N ="Versement Client Espece Num: ";
       public static String VERSEMENT_CLIENT_CHEQUE_N ="Versement Client Cheque Num: ";
       public static String VERSEMENT_CLIENT_BANCAIRE_N ="Versement Client Bancaire Num: ";
       
       public static String VERSEMENT_ESPECE_N ="Versement Espece Num: ";
       public static String VERSEMENT_CHEQUE_N ="Versement Cheque Num: ";
       public static String VERSEMENT_BANCAIRE_N ="Versement Bancaire Num: ";
       
       
       
       public static String SORTIE_SUR_TOURNEE ="Bon Sortie Client Num: ";
       public static String CREDIT_CHEQUE_CLIENT ="Credit Cheque Client Num: ";
       public static String CREDIT_BANCAIRE_CLIENT ="Credit Bancaire Client Num: ";
       public static String REGLEMENT_CHEQUE_CLIENT ="Reglement Cheque Client Num: ";
       public static String REGLEMENT_BANCAIRE_CLIENT ="Reglement Bancaire Client Num: ";
       public static String REGLEMENT_RECU_BANQUE_CLIENT ="Reglement Reçu Bancaire Client Num: ";
       public static String CREDIT_TRANSFERT_CHAUFFEUR ="Credit Transfert Chauffeur Num: ";
       
       public static String CREDIT_VERSEMENT ="Credit Versement Num: ";
       public static String REGLEMENT_ESPECE_SUR_TOURNEE ="Reglement Espece sur tournée Num: ";
       public static String REGLEMENT_CHEQUE_SUR_TOURNEE ="Reglement Cheque sur tournée Num: ";
       public static String REGLEMENT_RECU_BANQUE_SUR_TOURNEE ="Reglement Reçu Bancaire sur tournée Num: ";
       public static String CREDIT_SUR_TOURNEE_PRECEDENT ="Credit du Date ";
       public static String CREDIT_ESPECE_EN_CAISSE ="Credit Espece En Caisse Num: ";
       public static String CREDIT_CHEQUE_SUR_TOURNEE ="Credit Cheque sur tournée Num: ";
       public static String PAIEMENT_CHEQUE_N ="Paiement Cheque Num: ";
       public static String RETRAIT_CHEQUE_N ="Retrait Cheque Num: ";
       public static String SUR_TOURNEE_N =" sur tournée Num: ";
       public static String VENTE_SUR_TOURNEE ="Vente sur tournée Num: ";
       public static String REMISE_FACTURE ="Remise Facture Num: ";
       public static String REFUS_REMISE_FACTURE ="Refuse Remise Facture Num: ";
       public static String RETOUR_SUR_TOURNEE ="Retour sur tournée Num: ";
       public static String CHARGEMENT_SUR_TOURNEE ="Chargement sur tournée Num: ";
      
    //verifier les champs
       public static String CHAMP_INVALIDE="Nombre invalide";
    
    // verifier les champs de type "nombre"
       public static String CHAMP_VERIFIER="([0-9]+(\\.[0-9]+)?)+";
       public static String CHAMP_VERIFIER_EMAIL= "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
     
    //verifier le Montant
    public static String MONTANT_INVALIDE="Montant invalide";
   
    
    //Type Vente
   public static String SELECTIONNER= "Sélectionner...";
   public static String ETAT_TYPE_VENTE_D="D";
   public static String ETAT_TYPE_VENTE_G="G";   
   public static String ETAT_TYPE_VENTE_M="M";
    
   public static String ETAT_X="XXXX";   
   
   //Stock Etat
   public static String ETAT_STATUT_RECEPTION="Reception";
   public static String ETAT_STATUT_RETOUR_DEFINITIF="Retour Definitif";
   public static String ETAT_STATUT_TRANSFERT_SORTIES="Transfert Sorties";  
   public static String ETAT_STATUT_TRANSFERT_ENTREES="Transfert Entrees";
   public static String ETAT_STATUT_CHARGEMENT="Chargement"; 
   public static String ETAT_STATUT_CHARGEMENT_SUPP="Chargement Supp"; 
   public static String ETAT_STATUT_ORDINAIRE="Ordinaire";  
   public static String ETAT_STATUT_COMPLETIF="Completif"; 
   public static String ETAT_STATUT_SORTIES="Sorties";
   public static String ETAT_STATUT_INITIAL="Initial";
   
   public static String TYPE_CHEQUE_REMISE="Remise";
   public static String TYPE_CHEQUE_RETOUR="Retour";
   public static String TYPE_CHEQUE_NON_VERSE="Non Versé";

   public static String CATEGORIE_OFFRE="Offre";
   public static String CATEGORIE_VENTEE="Vente";

   public static String TYPE_VENDEUR_CAISSIER="Caissier";
   public static String TYPE_VENDEUR_VENDEUR="Vendeur";
   
   public static String ETAT_RETOUR_VENDEUR="Retour Vendeur";
   
   public static String SYSTEM_SUIVI_CHEQUE="Système Suivi Cheque";
   
   public static String ETAT_STATUT_VERSE="Versé";
   public static String ETAT_STATUT_PAYE="Payé";
   public static String ETAT_STATUT_IMPAYE="Impayé";
   public static String ETAT_STATUT_DEPOSE="Depose";
   public static String ETAT_STATUT_ESPECE="Payé Espèce";
   
   public static String SANS="Sans";

   public static String ETAT_ENVOYER="Envoyer";  
   public static String ETAT_RECU="Reçu";
   public static String ETAT_REPRISE="Reprise";
   public static String ETAT_PERTE="Perte";
    
   public static String ETAT_CHEQUE="Cheque";
   public static String ETAT_ESPECE="Espece";
   public static String ETAT_CREDIT="Credit";
   public static String CREDIT_CLIENT="Credit Client";
   
    /*Etat Statut*/
   
   public static String ETAT_STATUT_FERMER="Fermer"; 
   public static String ETAT_STATUT_VALIDER="Valider"; 
   public static String ETAT_STATUT_ATTENTE="En Attente";
   public static String ETAT_STATUT_LANCE="Lance";
   
   public static String DIMENSION="DIM";
   public static String RECEPTION="RCP";
   public static String CHARGEMENT="CHRG";
   public static String RETOUR="RTR";
   public static String VENTE="VET";
   public static String VERSEMENNT="VERS";
   public static String VERSEMENNT_C="C/VERS";
   public static String TRANSFERT="TRF";
   public static String SORTIES="SRT";
   public static String TRANSFERT_CHEQUE ="TRFC";
   public static String FACTURE="FACT";
   public static String OFFRE="OFF";
   public static String ARTICLE_OFFRE="ART/OFF";
   
    public static final int POUR_RECHERCHER=0;
    public static final int POUR_AJOUTER=0;
    public static final int POUR_MOUDIFIER=1;
    
//  public static final String style=" -fx-font-size: 12px;\n" + "-fx-font-weight: bold;\n"+ " -fx-text-fill: #333333;\n" + "-fx-effect: dropshadow( gaussian , rgba(255,255,255,0.5) , 0,0,0,1 );\n"  + "-fx-background-color: #a9a9a9 , white , white;\n"  + " -fx-background-insets: 0 -1 -1 -1, 0 0 0 0, 0 -1 3 -1;";
              
    
     /*CODE MENU*/
    public static final String COD_MENU_ARTICLE ="articlePane";
    public static final String COD_MENU_STOCK = "stockPane";
    public static final String COD_MENU_FACTURE = "facturePane";
    public static final String COD_MENU_REFERENTIEL = "referentielPane";
    public static final String COD_MENU_PARAMETRE = "parametrePane";

    
      //compteur Facture

    public static String CATEGORIE_FACTURE_NORMAL="Normal";
    public static String CATEGORIE_FACTURE_GRATUITE="Gratuité";
    public static String CATEGORIE_FACTURE_OFFRE="Offre";
    
    
    
    
    
    
}
