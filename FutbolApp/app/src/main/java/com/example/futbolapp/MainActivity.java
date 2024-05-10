package com.example.futbolapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;

public class MainActivity extends AppCompatActivity {
    private SQLiteHelper sqLiteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Creamos la instancia de SQLiteHelper
        sqLiteHelper = new SQLiteHelper(this, "Equipos", null, 1);

        // Cargamos los equipos en la Base de Datos
        crearEquipos();

        // Agregamos un retraso de 1 segundo antes de iniciar el Intent
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Cuando terminemos con MainActivity nos vamos a la pestaña de Mapa
                Intent intent = new Intent(MainActivity.this, Mapa.class);

                // En tu código al iniciar la nueva actividad
                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(MainActivity.this);
                startActivity(intent, options.toBundle());

            }
        }, 1000);
    }

    // Método para manejar el clic en la imagen
    public void pulsarImagen(View view) {
        Intent intent = new Intent(this, Mapa.class);
        startActivity(intent);
    }

    // Método para insertar las tuplas de los equipos
    protected void crearEquipos() {
        SQLiteDatabase db = sqLiteHelper.getWritableDatabase();

        // Borramos Equipos
        db.delete("Equipo", null, null);

        // Insertar Equipos 1 division
        ContentValues nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 1);
        nuevoRegistro.put("nombre", "Real Madrid");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion","El Real Madrid Club de Fútbol, fundado en 1902 en Madrid, es uno de los clubes más emblemáticos y exitosos del mundo. Conocido como \"Los Blancos\" o \"Los Merengues\", el equipo ha ganado numerosos títulos nacionales e internacionales, incluyendo múltiples títulos de La Liga y la Liga de Campeones de la UEFA. El Estadio Santiago Bernabéu es el hogar del club y es conocido por su historia y atmósfera única durante los partidos. El Real Madrid ha sido el hogar de algunos de los jugadores más legendarios de la historia del fútbol, como Cristiano Ronaldo, Alfredo Di Stéfano y Raúl González. Con una base de seguidores global y una reputación por su estilo de juego ofensivo y espectacular, el Real Madrid continúa siendo un símbolo de grandeza en el fútbol mundial.");
        nuevoRegistro.put("escudo", "escudorealmadrid");
        nuevoRegistro.put("nombreEstadio", "Estadio Santiago Bernabeu");
        nuevoRegistro.put("imagenEstadio", "estadiorealmadrid");
        nuevoRegistro.put("latitud", "40.45313629830775");
        nuevoRegistro.put("longitud", "-3.6882772003263673");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 2);
        nuevoRegistro.put("nombre", "FC Barcelona");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "El Fútbol Club Barcelona, fundado en 1899 en Barcelona, es uno de los clubes más reconocidos y exitosos del mundo. Conocido como \"Barça\", el equipo ha ganado numerosos títulos nacionales e internacionales, incluyendo múltiples títulos de La Liga y la Liga de Campeones de la UEFA. El Camp Nou es el hogar del club y es el estadio más grande de Europa, conocido por su atmósfera única y apasionada durante los partidos. El Barcelona ha sido el hogar de algunos de los jugadores más icónicos de la historia del fútbol, como Lionel Messi, Johan Cruyff y Xavi Hernández. Con una base de seguidores global y una reputación por su estilo de juego creativo y ofensivo, el Barcelona continúa siendo un referente en el fútbol mundial.");
        nuevoRegistro.put("escudo", "escudobarcelona");
        nuevoRegistro.put("nombreEstadio", "Spotify Camp Nou");
        nuevoRegistro.put("imagenEstadio", "estadiobarcelona");
        nuevoRegistro.put("latitud", "41.38102477411609");
        nuevoRegistro.put("longitud", "2.123560088168574");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 3);
        nuevoRegistro.put("nombre", "Girona");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Girona Fútbol Club, con sede en Girona, Cataluña, España, ha experimentado un ascenso meteórico en las últimas temporadas, alcanzando La Liga por primera vez en 2017-2018. A pesar de ser un recién llegado, ha demostrado ser un competidor digno con un estilo de juego valiente. El equipo ha mantenido su posición en la máxima categoría y desafiado a los clubes más grandes de España. Aunque el Girona tiene una historia predominante en las divisiones inferiores, ha cultivado una base de seguidores leales y aspira a seguir creciendo y alcanzando nuevos logros en el futuro. Su presencia en La Liga ha contribuido a la visibilidad del fútbol catalán a nivel nacional e internacional, consolidando su papel como un actor destacado en el panorama futbolístico español. Con un enfoque en el desarrollo de talento local y una gestión sólida, el Girona continúa trabajando para establecerse como un club respetado y competitivo en el fútbol español.");
        nuevoRegistro.put("escudo", "escudogirona");
        nuevoRegistro.put("nombreEstadio", "Estadio Montilivi");
        nuevoRegistro.put("imagenEstadio", "estadiogirona");
        nuevoRegistro.put("latitud", "41.96120615223275");
        nuevoRegistro.put("longitud", "2.827744469653815");
        db.insert("Equipo", null, nuevoRegistro);


        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 4);
        nuevoRegistro.put("nombre", "Atlético de Madrid");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Club Atlético de Madrid es uno de los clubes de fútbol más destacados y emblemáticos de España, con sede en Madrid. Fundado en 1903, el Atlético ha sido un competidor constante tanto a nivel nacional como internacional.\n" +
                "\n" +
                "Conocido como \"Los Colchoneros\" por el tradicional color de sus camisetas, el Atlético ha tenido una rica historia llena de éxitos. Ha ganado múltiples títulos de La Liga, así como copas nacionales e internacionales, incluyendo la Liga de Campeones de la UEFA.\n" +
                "\n" +
                "El equipo ha contado con una serie de jugadores legendarios a lo largo de los años, incluidos Diego Simeone, Fernando Torres y Luis Aragonés, entre otros. Además, el club es conocido por su ferviente base de seguidores y su estadio emblemático, el Wanda Metropolitano.");
        nuevoRegistro.put("escudo", "escudoatleticomadrid");
        nuevoRegistro.put("nombreEstadio", "Estadio Cívitas Metropolitano");
        nuevoRegistro.put("imagenEstadio", "estadioatleticomadrid");
        nuevoRegistro.put("latitud", "40.43631804316178");
        nuevoRegistro.put("longitud", "-3.5992244079342233");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 5);
        nuevoRegistro.put("nombre", "Athletic");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "El Athletic Club, fundado en 1898 en Bilbao, es uno de los clubes más antiguos y emblemáticos de España. Conocido como \"Los Leones\" por su feroz espíritu competitivo, el Athletic ha mantenido una política de fichajes centrada en jugadores vascos o formados en la cantera del club. Ha ganado varios títulos de La Liga y ha sido un competidor constante en el fútbol español. Su estadio, San Mamés, es uno de los más icónicos de España y es conocido por su atmósfera única y apasionada. A lo largo de los años, el Athletic ha contado con jugadores legendarios como Telmo Zarra y José Ángel Iribar, quienes han dejado una huella indeleble en la historia del club. Con una base de seguidores leales y una identidad única, el Athletic continúa siendo un símbolo del orgullo vasco y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoathletic");
        nuevoRegistro.put("nombreEstadio", "Estadio de San Mamés");
        nuevoRegistro.put("imagenEstadio", "estadioathletic");
        nuevoRegistro.put("latitud", "43.2641158818213");
        nuevoRegistro.put("longitud", "-2.949137753600668");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 6);
        nuevoRegistro.put("nombre", "Betis");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "El Real Betis Balompié, fundado en 1907 en Sevilla, es uno de los clubes más destacados del fútbol español. Conocido como \"Los Verdiblancos\", el Betis tiene una rica historia y una base de seguidores apasionados. Ha ganado varios títulos nacionales e internacionales a lo largo de los años, incluyendo la Copa del Rey. El estadio del club, el Estadio Benito Villamarín, es un lugar emblemático donde los aficionados disfrutan de una atmósfera única durante los partidos. El Betis ha contado con jugadores talentosos y emblemáticos, como Joaquín Sánchez y Rubén Castro, quienes han dejado una huella imborrable en la historia del club. Con un estilo de juego distintivo y una identidad arraigada en la cultura sevillana, el Betis continúa siendo un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudobetis");
        nuevoRegistro.put("nombreEstadio", "Estadio Benito Villamarín");
        nuevoRegistro.put("imagenEstadio", "estadiobetis");
        nuevoRegistro.put("latitud", "37.3564580597294");
        nuevoRegistro.put("longitud", "-5.981808261113377");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 7);
        nuevoRegistro.put("nombre", "Real Sociedad");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "La Real Sociedad de Fútbol, fundada en 1909 en San Sebastián, es uno de los clubes más emblemáticos del fútbol español. Conocida como \"La Real\", el equipo tiene una rica historia y una base de seguidores apasionados en el País Vasco. Ha ganado varios títulos nacionales e internacionales, incluyendo dos títulos de La Liga y dos de la Copa del Rey. El Estadio Anoeta es el hogar del club y es conocido por su ambiente vibrante durante los partidos. La Real Sociedad ha sido el hogar de jugadores destacados a lo largo de los años, como Xabi Alonso y Mikel Arteta, quienes han dejado una marca duradera en la historia del club. Con un estilo de juego atractivo y una identidad arraigada en la cultura vasca, La Real continúa siendo un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudorealsociedad");
        nuevoRegistro.put("nombreEstadio", "Reale Arena (Anoeta)");
        nuevoRegistro.put("imagenEstadio", "estadiorealsociedad");
        nuevoRegistro.put("latitud", "43.30146755142602");
        nuevoRegistro.put("longitud", "-1.9735896145400782");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 8);
        nuevoRegistro.put("nombre", "U.D. Las Palmas");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "La Unión Deportiva Las Palmas, fundada en 1949 en Las Palmas de Gran Canaria, es un club de fútbol destacado en el panorama español. Conocido como \"La Unión\", el equipo tiene una base de seguidores apasionados en las Islas Canarias y más allá. Aunque ha tenido altibajos en su historia, ha competido en La Liga en varias ocasiones y ha logrado momentos destacados en competiciones nacionales e internacionales. El Estadio de Gran Canaria es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. La UD Las Palmas ha sido el hogar de jugadores talentosos a lo largo de los años y ha contribuido al desarrollo del fútbol en la región. Con un estilo de juego distintivo y una identidad arraigada en la cultura canaria, La Unión Deportiva Las Palmas continúa siendo un símbolo del orgullo local y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudolaspalmas");
        nuevoRegistro.put("nombreEstadio", "Estadio de Gran Canaria");
        nuevoRegistro.put("imagenEstadio", "estadiolaspalmas");
        nuevoRegistro.put("latitud", "28.10021694926606");
        nuevoRegistro.put("longitud", "-15.45673348822338");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 9);
        nuevoRegistro.put("nombre", "Valencia C.F");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Valencia Club de Fútbol, fundado en 1919 en Valencia, es uno de los clubes más destacados del fútbol español. Conocido como \"Los Che\", el equipo tiene una rica historia y una base de seguidores apasionados en la región de Valencia y más allá. Ha ganado varios títulos nacionales e internacionales, incluyendo múltiples títulos de La Liga y la Copa del Rey. El Estadio Mestalla es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Valencia ha sido el hogar de jugadores talentosos a lo largo de los años, como David Villa y David Silva, quienes han dejado una marca duradera en la historia del club. Con un estilo de juego atractivo y una identidad arraigada en la cultura valenciana, el Valencia continúa siendo un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudovalencia");
        nuevoRegistro.put("nombreEstadio", "Estadio de Mestalla");
        nuevoRegistro.put("imagenEstadio", "estadiovalencia");
        nuevoRegistro.put("latitud", "39.47451206168492");
        nuevoRegistro.put("longitud", "-0.3581948878441397");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 10);
        nuevoRegistro.put("nombre", "Getafe");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Getafe Club de Fútbol, fundado en 1983 en Getafe, es un club relativamente joven en el fútbol español que ha experimentado un ascenso meteórico en las últimas décadas. Conocido como \"Los Azulones\", el equipo ha ganado reconocimiento por su estilo de juego aguerrido y su capacidad para desafiar a equipos más establecidos. Aunque ha tenido altibajos en su historia, el Getafe ha competido en La Liga en varias ocasiones y ha logrado alcanzar competiciones europeas. El Coliseum Alfonso Pérez es el hogar del club y es conocido por su ambiente animado durante los partidos. El Getafe ha sido el hogar de jugadores talentosos y ha contribuido al desarrollo del fútbol en la Comunidad de Madrid. Con una identidad en constante evolución y una base de seguidores leales, el Getafe continúa siendo un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudogetafe");
        nuevoRegistro.put("nombreEstadio", "Estadio Coliseum");
        nuevoRegistro.put("imagenEstadio", "estadiogetafe");
        nuevoRegistro.put("latitud", "40.32567641545213");
        nuevoRegistro.put("longitud", "-3.714782926670201");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 11);
        nuevoRegistro.put("nombre", "Osasuna");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "El Club Atlético Osasuna, fundado en 1920 en Pamplona, es uno de los clubes más importantes de la región de Navarra y un competidor respetado en el fútbol español. Conocido como \"Los Rojillos\", el equipo ha tenido una presencia constante en La Liga y ha ganado títulos importantes a lo largo de su historia. El Estadio El Sadar es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. Osasuna ha sido el hogar de jugadores talentosos y ha desarrollado una reputación por su estilo de juego aguerrido y luchador. Con una base de seguidores leales en Pamplona y en toda Navarra, el Club Atlético Osasuna continúa siendo un símbolo del orgullo regional y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoosasuna");
        nuevoRegistro.put("nombreEstadio", "Estadio El Sadar");
        nuevoRegistro.put("imagenEstadio", "estadioosasuna");
        nuevoRegistro.put("latitud", "42.796605718706346");
        nuevoRegistro.put("longitud", "-1.6370131131340282");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 12);
        nuevoRegistro.put("nombre", "Alavés");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Deportivo Alavés, fundado en 1921 en Vitoria-Gasteiz, es un club de fútbol con una rica historia en el fútbol español. Conocido como \"El Glorioso\", el equipo ha experimentado diferentes períodos de éxito a lo largo de su historia y ha competido tanto en La Liga como en competiciones europeas. El Estadio de Mendizorroza es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Alavés ha sido el hogar de jugadores talentosos y ha mantenido una base de seguidores leales en la región de Álava y más allá. Aunque ha tenido altibajos en su historia, el Deportivo Alavés continúa siendo un competidor respetado en el fútbol español y un símbolo del orgullo local en Vitoria-Gasteiz.");
        nuevoRegistro.put("escudo", "escudoalaves");
        nuevoRegistro.put("nombreEstadio", "Estadio de Mendizorroza");
        nuevoRegistro.put("imagenEstadio", "estadioalaves");
        nuevoRegistro.put("latitud", "42.83711079194527");
        nuevoRegistro.put("longitud", "-2.6883810372197576");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 13);
        nuevoRegistro.put("nombre", "Villarreal");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Villarreal Club de Fútbol, fundado en 1923 en Villarreal, es un club destacado en el fútbol español, conocido como \"El Submarino Amarillo\". El equipo ha tenido un ascenso notable en las últimas décadas, pasando de las divisiones inferiores a consolidarse como un competidor estable en La Liga y competiciones europeas. El Estadio de la Cerámica es el hogar del club y es conocido por su ambiente vibrante durante los partidos. El Villarreal ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego técnico y ofensivo. Con una base de seguidores apasionados en la región de Castellón y más allá, el Villarreal continúa siendo un competidor respetado en el fútbol español y un símbolo del éxito en el fútbol regional.");
        nuevoRegistro.put("escudo", "escudovillarreal");
        nuevoRegistro.put("nombreEstadio", "Estadio de la Cerámica");
        nuevoRegistro.put("imagenEstadio", "estadiovillarreal");
        nuevoRegistro.put("latitud", "39.94410786007454");
        nuevoRegistro.put("longitud", "-0.10349215313618856");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 14);
        nuevoRegistro.put("nombre", "RayoVallecano");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Rayo Vallecano de Madrid, fundado en 1924 en Vallecas, es un club de fútbol con una fuerte identidad y una base de seguidores apasionados en la comunidad de Madrid. Conocido como \"Los Franjirrojos\", el equipo ha competido en varias temporadas en La Liga y ha tenido un papel destacado en el fútbol español. El Estadio de Vallecas es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Rayo Vallecano ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego valiente y comprometido. A pesar de los desafíos financieros y deportivos, el Rayo Vallecano continúa siendo un símbolo de la lucha y el orgullo local en Vallecas y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudorayovallecano");
        nuevoRegistro.put("nombreEstadio", "Estadio de Vallecas");
        nuevoRegistro.put("imagenEstadio", "estadiorayovallecano");
        nuevoRegistro.put("latitud", "40.39182500092765");
        nuevoRegistro.put("longitud", "-3.6585251113247277");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 15);
        nuevoRegistro.put("nombre", "Sevilla");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Sevilla Fútbol Club, fundado en 1890 en Sevilla, es uno de los clubes más destacados y exitosos del fútbol español. Conocido como \"Los Nervionenses\" o \"Los Rojiblancos\", el equipo ha ganado múltiples títulos nacionales e internacionales, incluyendo la Liga Europa de la UEFA y la Supercopa de la UEFA. El Estadio Ramón Sánchez Pizjuán es el hogar del club y es conocido por su atmósfera vibrante y su apoyo incondicional durante los partidos. El Sevilla ha sido el hogar de jugadores de renombre mundial y ha ganado reconocimiento por su estilo de juego dinámico y ofensivo. Con una base de seguidores apasionados en Andalucía y en todo el mundo, el Sevilla continúa siendo un competidor respetado en el fútbol español y un símbolo de orgullo para la ciudad de Sevilla.");
        nuevoRegistro.put("escudo", "escudosevilla");
        nuevoRegistro.put("nombreEstadio", "Estadio Ramón Sánchez-Pizjuán");
        nuevoRegistro.put("imagenEstadio", "estadiosevilla");
        nuevoRegistro.put("latitud", "37.38390724784859");
        nuevoRegistro.put("longitud", "-5.970548648145264");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 16);
        nuevoRegistro.put("nombre", "R.C.D. Mallorca");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "El Real Club Deportivo Mallorca, fundado en 1916 en Palma de Mallorca, es uno de los clubes de fútbol más destacados de las Islas Baleares y del fútbol español en general. Conocido como \"Los Bermellones\" o \"Los Barralets\", el equipo ha experimentado varios períodos de éxito a lo largo de su historia, compitiendo tanto en La Liga como en competiciones europeas. El Estadio de Son Moix es el hogar del club y es conocido por su ambiente vibrante durante los partidos. El Mallorca ha sido el hogar de jugadores talentosos y ha mantenido una base de seguidores leales en la isla y más allá. A pesar de los desafíos financieros y deportivos, el Real Mallorca continúa siendo un símbolo de orgullo para la comunidad balear y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudomallorca");
        nuevoRegistro.put("nombreEstadio", "Estadio de Son Moix");
        nuevoRegistro.put("imagenEstadio", "estadiomallorca");
        nuevoRegistro.put("latitud", "39.58991869581871");
        nuevoRegistro.put("longitud", "2.6300744694105296");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 17);
        nuevoRegistro.put("nombre", "Celta de Vigo");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Real Club Celta de Vigo, fundado en 1923 en Vigo, es uno de los clubes más emblemáticos y queridos de Galicia y del fútbol español en general. Conocido como \"Os Celestes\" o \"Os Celtiñas\", el equipo ha tenido una presencia constante en La Liga y ha competido en competiciones europeas en varias ocasiones. El Estadio de Balaídos es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Celta de Vigo ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego técnico y ofensivo. Con una base de seguidores apasionados en Galicia y más allá, el Celta de Vigo continúa siendo un competidor respetado en el fútbol español y un símbolo del orgullo regional en Vigo y toda Galicia.");
        nuevoRegistro.put("escudo", "escudoceltavigo");
        nuevoRegistro.put("nombreEstadio", "Estadio de Balaídos");
        nuevoRegistro.put("imagenEstadio", "estadioceltavigo");
        nuevoRegistro.put("latitud", "42.2119200712097");
        nuevoRegistro.put("longitud", "-8.739493238275841");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 18);
        nuevoRegistro.put("nombre", "Cádiz");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "El Cádiz Club de Fútbol, fundado en 1910 en Cádiz, es uno de los clubes más antiguos y queridos del fútbol español. Conocido como \"Los Amarillos\" o \"Los Submarinos Amarillos\", el equipo ha competido en varias temporadas en La Liga y ha tenido un papel destacado en el fútbol español. El Estadio Ramón de Carranza es el hogar del club y es conocido por su ambiente vibrante durante los partidos. El Cádiz ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego sólido y disciplinado. Con una base de seguidores leales en Cádiz y más allá, el Cádiz Club de Fútbol continúa siendo un símbolo del orgullo local y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudocadiz");
        nuevoRegistro.put("nombreEstadio", "Estadio Nuevo Mirandilla");
        nuevoRegistro.put("imagenEstadio", "estadiocadiz");
        nuevoRegistro.put("latitud", "36.50257943960053");
        nuevoRegistro.put("longitud", "-6.272885584655704");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 19);
        nuevoRegistro.put("nombre", "Granada");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Granada Club de Fútbol, fundado en 1931 en Granada, es un club con una historia rica y una base de seguidores apasionados en Andalucía y más allá. Conocido como \"El Graná\" o \"El equipo nazarí\", el club ha tenido un ascenso meteórico en las últimas temporadas, compitiendo en La Liga y en competiciones europeas. El Estadio Nuevo Los Cármenes es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Granada ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego valiente y competitivo. Con una base de seguidores leales en Granada y una comunidad futbolística en crecimiento, el Granada Club de Fútbol continúa siendo un símbolo de orgullo para la ciudad y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudogranada");
        nuevoRegistro.put("nombreEstadio", "Estadio Los Cármenes");
        nuevoRegistro.put("imagenEstadio", "estadiogranada");
        nuevoRegistro.put("latitud", "37.15286756747166");
        nuevoRegistro.put("longitud", "-3.5958750764608576");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 20);
        nuevoRegistro.put("nombre", "Álmeria");
        nuevoRegistro.put("division", 1);
        nuevoRegistro.put("descripcion", "\n" +
                "El Club de Fútbol de la Unión Deportiva Almería, fundado en 1989 en Almería, es un club relativamente joven en el panorama del fútbol español. Conocido como \"Los Rojiblancos\" o \"Los Indálicos\", el equipo ha competido en varias temporadas en La Liga y en la Segunda División, mostrando un desempeño sólido a lo largo de los años. El Estadio de los Juegos Mediterráneos es el hogar del club y es conocido por su ambiente vibrante durante los partidos. La UD Almería ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego competitivo y comprometido. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Almería y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoalmeria");
        nuevoRegistro.put("nombreEstadio", "Power Horse Stadium");
        nuevoRegistro.put("imagenEstadio", "estadioalmeria");
        nuevoRegistro.put("latitud", "36.839992653691354");
        nuevoRegistro.put("longitud", "-2.4355095034493552");
        db.insert("Equipo", null, nuevoRegistro);

        // Insertar Equipos 2 division
        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 21);
        nuevoRegistro.put("nombre", "Leganés");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "El Club Deportivo Leganés, fundado en 1928 en Leganés, es un club de fútbol con sede en la Comunidad de Madrid. Conocido como \"Los Pepineros\", el equipo ha competido en varias temporadas en La Liga y en la Segunda División, mostrando un desempeño sólido en su historial. El Estadio Municipal de Butarque es el hogar del club y es conocido por su ambiente animado durante los partidos. El Leganés ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego disciplinado y comprometido. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Leganés y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoleganes");
        nuevoRegistro.put("nombreEstadio", "Estadio Municipal de Butarque");
        nuevoRegistro.put("imagenEstadio", "estadioleganes");
        nuevoRegistro.put("latitud", "40.34037478443268");
        nuevoRegistro.put("longitud", "-3.7607086898996176");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 22);
        nuevoRegistro.put("nombre", "Eibar");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "La Sociedad Deportiva Eibar, fundada en 1940 en Eibar, es un club de fútbol con sede en el País Vasco. Conocido como \"Los Armeros\" o \"El Eibar\", el equipo ha logrado ascender a La Liga y se ha establecido como un competidor respetado en la primera división española. El Estadio Municipal de Ipurua es el hogar del club y es conocido por su atmósfera vibrante y su cercanía entre los aficionados y el terreno de juego. El Eibar ha ganado reconocimiento por su estilo de juego aguerrido y su capacidad para desafiar a equipos más establecidos. Aunque es un club relativamente pequeño, el Eibar ha demostrado su valía en el fútbol español y continúa siendo un símbolo de orgullo para la ciudad de Eibar y para toda la región vasca.");
        nuevoRegistro.put("escudo", "escudoeibar");
        nuevoRegistro.put("nombreEstadio", "Estadio Municipal de Ipurua");
        nuevoRegistro.put("imagenEstadio", "estadioeibar");
        nuevoRegistro.put("latitud", "43.18172592447909");
        nuevoRegistro.put("longitud", "-2.4757714419629733");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 23);
        nuevoRegistro.put("nombre", "Racing Ferrol");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "El Racing Club de Ferrol, fundado en 1919 en Ferrol, es un club de fútbol con una rica historia en el fútbol español. Conocido como \"Los Racinguistas\", el equipo ha competido en varias temporadas en diferentes divisiones del fútbol español, incluyendo la Segunda División B y la Tercera División. El Estadio Municipal de A Malata es el hogar del club y es conocido por su ambiente animado durante los partidos. A lo largo de los años, el Racing de Ferrol ha sido el hogar de jugadores talentosos y ha mantenido una base de seguidores leales en la región de Galicia. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Ferrol y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoracingferrol");
        nuevoRegistro.put("nombreEstadio", "Estadio Municipal de A Malata");
        nuevoRegistro.put("imagenEstadio", "estadioracingferrol");
        nuevoRegistro.put("latitud", "43.49119346633314");
        nuevoRegistro.put("longitud", "-8.23950666100653");
        db.insert("Equipo", null, nuevoRegistro);


        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 24);
        nuevoRegistro.put("nombre", "Sporting");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Real Sporting de Gijón, fundado en 1905 en Gijón, es uno de los clubes de fútbol más emblemáticos de Asturias y del fútbol español en general. Conocido como \"Los Rojiblancos\" o \"El Sporting\", el equipo ha tenido una presencia constante en La Liga y en la Segunda División, mostrando un desempeño sólido a lo largo de su historia. El Estadio El Molinón es el hogar del club y es el estadio más antiguo de España, conocido por su historia y su atmósfera única durante los partidos. El Sporting de Gijón ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego dinámico y ofensivo. Con una base de seguidores apasionados en Asturias y más allá, el Real Sporting de Gijón continúa siendo un símbolo de orgullo para la ciudad y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudosporting");
        nuevoRegistro.put("nombreEstadio", "Estadio El Molinón");
        nuevoRegistro.put("imagenEstadio", "estadiosporting");
        nuevoRegistro.put("latitud", "43.536259759858766");
        nuevoRegistro.put("longitud", "-5.637577849366358");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 25);
        nuevoRegistro.put("nombre", "Espanyol");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "El Reial Club Deportiu Espanyol de Barcelona, \u200B\u200Bcomúnmente conocido como Espanyol, es uno de los clubes de fútbol más antiguos y emblemáticos de Cataluña y del fútbol español en general. Fundado en 1900 en Barcelona, el Espanyol ha competido en numerosas temporadas en La Liga, mostrando un desempeño sólido a lo largo de su historia. El equipo juega sus partidos en casa en el Estadio RCDE, conocido por su ambiente vibrante durante los partidos. El Espanyol ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego técnico y disciplinado. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Barcelona y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoespanyol");
        nuevoRegistro.put("nombreEstadio", "Estadio RCDE Stadium");
        nuevoRegistro.put("imagenEstadio", "estadioespanyol");
        nuevoRegistro.put("latitud", "41.347828455310335");
        nuevoRegistro.put("longitud", "2.075451991873017");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 26);
        nuevoRegistro.put("nombre", "Valladolid");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Real Valladolid Club de Fútbol, fundado en 1928 en Valladolid, es un club con una rica historia en el fútbol español. Conocido como \"Los Pucelanos\" o \"El Pucela\", el equipo ha competido en varias temporadas en La Liga y ha tenido un papel destacado en el fútbol español. El Estadio José Zorrilla es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Valladolid ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego aguerrido y comprometido. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Valladolid y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudovalladolid");
        nuevoRegistro.put("nombreEstadio", "Estadio José Zorrilla");
        nuevoRegistro.put("imagenEstadio", "estadiovalladolid");
        nuevoRegistro.put("latitud", "41.64446212928984");
        nuevoRegistro.put("longitud", "-4.761156069311408");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 27);
        nuevoRegistro.put("nombre", "Elche");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "El Elche Club de Fútbol, fundado en 1923 en Elche, es un club con una larga trayectoria en el fútbol español. Conocido como \"Los Franjiverdes\", el equipo ha competido en varias temporadas en La Liga y ha tenido un papel destacado en el fútbol español. El Estadio Martínez Valero es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Elche ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego disciplinado y competitivo. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Elche y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoelche");
        nuevoRegistro.put("nombreEstadio", "Estadio Martínez Valero");
        nuevoRegistro.put("imagenEstadio", "estadioelche");
        nuevoRegistro.put("latitud", "38.26701635843382");
        nuevoRegistro.put("longitud", "-0.6631973380957566");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 28);
        nuevoRegistro.put("nombre", "Racing Satander");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Real Racing Club de Santander, fundado en 1913 en Santander, es uno de los clubes más antiguos y emblemáticos del fútbol español. Conocido como \"Los Racinguistas\" o \"El Racing\", el equipo ha competido en varias temporadas en La Liga y ha tenido un papel destacado en el fútbol español. El Estadio El Sardinero es el hogar del club y es conocido por su historia y atmósfera única durante los partidos. El Racing de Santander ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego aguerrido y competitivo. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Santander y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoracingsantander");
        nuevoRegistro.put("nombreEstadio", "Estadio El Sardinero");
        nuevoRegistro.put("imagenEstadio", "estadioracingsantander");
        nuevoRegistro.put("latitud", "43.476352769427905");
        nuevoRegistro.put("longitud", "-3.7933952186789157");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 29);
        nuevoRegistro.put("nombre", "Burgos CF");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "El Burgos Club de Fútbol, fundado en 1922 en Burgos, es un club con una larga historia en el fútbol español. Conocido como \"Los Blanquinegros\" o \"El Burgos CF\", el equipo ha competido en varias categorías del fútbol español, incluyendo la Segunda División B y la Tercera División. El Estadio Municipal El Plantío es el hogar del club y es conocido por su ambiente animado durante los partidos. A lo largo de los años, el Burgos CF ha sido el hogar de jugadores talentosos y ha mantenido una base de seguidores leales en la ciudad de Burgos y sus alrededores. A pesar de los desafíos que ha enfrentado, el club continúa siendo un símbolo de orgullo para la comunidad local y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoburgos");
        nuevoRegistro.put("nombreEstadio", "Estadio Municipal El Plantío");
        nuevoRegistro.put("imagenEstadio", "estadioburgos");
        nuevoRegistro.put("latitud", "42.34420922647513");
        nuevoRegistro.put("longitud", "-3.680633457669717");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 30);
        nuevoRegistro.put("nombre", "Oviedo");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Real Oviedo es un club de fútbol fundado en 1926 en Oviedo, Asturias. Conocido como \"Los Azules\" o \"Los Carbayones\", el equipo ha tenido una presencia constante en el fútbol español a lo largo de su historia. El club ha competido en varias temporadas en La Liga y en la Segunda División, y ha ganado reconocimiento por su estilo de juego aguerrido y competitivo. El Estadio Carlos Tartiere es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Real Oviedo ha sido el hogar de jugadores talentosos y ha mantenido una base de seguidores apasionados en Asturias y más allá. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Oviedo y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudooviedo");
        nuevoRegistro.put("nombreEstadio", "Estadio Carlos Tartiere");
        nuevoRegistro.put("imagenEstadio", "estadiooviedo");
        nuevoRegistro.put("latitud", "43.36072696937512");
        nuevoRegistro.put("longitud", "-5.870198486139934");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 31);
        nuevoRegistro.put("nombre", "Levante");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Levante Unión Deportiva, fundado en 1909 en Valencia, es uno de los clubes más destacados del fútbol español. Conocido como \"Los Granotas\" o \"El Levante\", el equipo ha competido en varias temporadas en La Liga y ha tenido un papel destacado en el fútbol español. El Estadio Ciudad de Valencia es el hogar del club y es conocido por su atmósfera vibrante durante los partidos. El Levante ha sido el hogar de jugadores talentosos y ha ganado reconocimiento por su estilo de juego aguerrido y competitivo. Aunque ha tenido altibajos en su historia, el club continúa siendo un símbolo de orgullo para la ciudad de Valencia y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudolevante");
        nuevoRegistro.put("nombreEstadio", "Estadio Ciudad de Valencia");
        nuevoRegistro.put("imagenEstadio", "estadiolevante");
        nuevoRegistro.put("latitud", "39.49460807889756");
        nuevoRegistro.put("longitud", "-0.3639268187502568");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 32);
        nuevoRegistro.put("nombre", "Zaragoza");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Real Zaragoza es un club de fútbol con sede en Zaragoza, España, fundado en 1932. Conocido como \"Los Maños\" o \"El Zaragoza\", el equipo tiene una larga y rica historia en el fútbol español. Ha competido en varias temporadas en La Liga y ha ganado numerosos títulos nacionales e internacionales, incluyendo la Copa del Rey y la Recopa de Europa. El equipo juega sus partidos como local en el Estadio La Romareda, conocido por su atmósfera vibrante durante los partidos. A lo largo de los años, el Zaragoza ha sido el hogar de jugadores talentosos y ha mantenido una base de seguidores apasionados en Zaragoza y más allá. Aunque ha enfrentado desafíos en su historia, el Real Zaragoza continúa siendo un símbolo de orgullo para la ciudad y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudozaragoza");
        nuevoRegistro.put("nombreEstadio", "Estadio de La Romareda");
        nuevoRegistro.put("imagenEstadio", "estadiozaragoza");
        nuevoRegistro.put("latitud", "41.63641352350871");
        nuevoRegistro.put("longitud", "-0.9018780456963292");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 33);
        nuevoRegistro.put("nombre", "Eldense");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Club Deportivo Eldense es un club de fútbol español con sede en Elda, Alicante. Fundado en 1921, el equipo ha competido principalmente en categorías regionales y ha tenido participaciones esporádicas en categorías nacionales, incluyendo la Segunda División B. El club juega sus partidos como local en el Estadio Nuevo Pepico Amat. A lo largo de su historia, el Eldense ha sido un punto focal en la comunidad local de Elda y ha contribuido al desarrollo del fútbol en la región de Alicante. Aunque no ha tenido un éxito destacado a nivel nacional, el club continúa siendo un símbolo de orgullo para la ciudad y un lugar donde los aficionados pueden disfrutar del deporte rey.");
        nuevoRegistro.put("escudo", "escudoeldense");
        nuevoRegistro.put("nombreEstadio", "Estadio Nuevo Pepico Amat");
        nuevoRegistro.put("imagenEstadio", "estadioeldense");
        nuevoRegistro.put("latitud", "38.4674755919431");
        nuevoRegistro.put("longitud", "-0.7960662600105154");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 34);
        nuevoRegistro.put("nombre", "Mirandés");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "El Club Deportivo Mirandés, comúnmente conocido como Mirandés, es un club de fútbol español con sede en Miranda de Ebro, provincia de Burgos. Fundado en 1927, el Mirandés ha competido principalmente en categorías regionales y ha tenido algunas participaciones en categorías nacionales, incluyendo la Segunda División y la Copa del Rey. El equipo juega sus partidos como local en el Estadio Municipal de Anduva, conocido por su atmósfera vibrante durante los encuentros. A lo largo de su historia, el Mirandés ha sido un punto de referencia en la comunidad local y ha desarrollado una base de seguidores apasionados. Aunque no ha tenido un éxito destacado a nivel nacional, el club continúa siendo un símbolo de orgullo para Miranda de Ebro y un lugar donde los aficionados disfrutan del fútbol.");
        nuevoRegistro.put("escudo", "escudomirandes");
        nuevoRegistro.put("nombreEstadio", "Estadio Municipal de Anduva");
        nuevoRegistro.put("imagenEstadio", "estadiomirandes");
        nuevoRegistro.put("latitud", "42.68090303050849");
        nuevoRegistro.put("longitud", "-2.93540584470452");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 35);
        nuevoRegistro.put("nombre", "Tenerife");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "El Club Deportivo Tenerife, conocido comúnmente como CD Tenerife, es un club de fútbol español con sede en Santa Cruz de Tenerife, en las Islas Canarias. Fundado en 1922, el equipo ha competido principalmente en La Liga y la Segunda División del fútbol español. El CD Tenerife juega sus partidos como local en el Estadio Heliodoro Rodríguez López, conocido por su atmósfera vibrante y su ubicación en medio de las montañas. A lo largo de su historia, el club ha ganado varios títulos de Segunda División y ha tenido apariciones en competiciones europeas. El CD Tenerife ha sido un punto de referencia en la comunidad local y ha desarrollado una base de seguidores apasionados en las Islas Canarias y más allá.");
        nuevoRegistro.put("escudo", "escudotenerife");
        nuevoRegistro.put("nombreEstadio", "Estadio Heliodoro Rodríguez López");
        nuevoRegistro.put("imagenEstadio", "estadiotenerife");
        nuevoRegistro.put("latitud", "28.46329777576077");
        nuevoRegistro.put("longitud", "-16.26072282076019");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 36);
        nuevoRegistro.put("nombre", "Huesca");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "La Sociedad Deportiva Huesca, conocida comúnmente como SD Huesca, es un club de fútbol español con sede en la ciudad de Huesca, en la comunidad autónoma de Aragón. Fundado en 1960, el equipo ha competido en varias divisiones del fútbol español, ascendiendo gradualmente hasta alcanzar La Liga, la máxima categoría del fútbol español, en la que ha participado en varias temporadas. El SD Huesca juega sus partidos como local en el Estadio El Alcoraz, conocido por su atmósfera animada y su ubicación pintoresca en las afueras de Huesca. A lo largo de su historia, el club ha desarrollado una base de seguidores leales en la región de Aragón y ha ganado reconocimiento por su estilo de juego valiente y competitivo. Aunque ha enfrentado desafíos, el SD Huesca continúa siendo un símbolo de orgullo para la ciudad de Huesca y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudohuesca");
        nuevoRegistro.put("nombreEstadio", "Estadio El Alcoraz");
        nuevoRegistro.put("imagenEstadio", "estadiohuesca");
        nuevoRegistro.put("latitud", "42.131741103972246");
        nuevoRegistro.put("longitud", "-0.4247503865066593");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 37);
        nuevoRegistro.put("nombre", "Cartagena");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Fútbol Club Cartagena, comúnmente conocido como FC Cartagena, es un club de fútbol español con sede en la ciudad de Cartagena, en la Región de Murcia. Fundado en 1995, el equipo ha competido principalmente en las divisiones inferiores del fútbol español, con participaciones esporádicas en la Segunda División y la Segunda División B. El FC Cartagena juega sus partidos como local en el Estadio Municipal Cartagonova, que cuenta con una capacidad para albergar a más de 15,000 espectadores. A lo largo de su historia, el club ha ganado reconocimiento por su compromiso con el desarrollo del fútbol en la región de Murcia y ha desarrollado una base de seguidores leales en la ciudad de Cartagena y sus alrededores. Aunque ha enfrentado desafíos en su historia, el FC Cartagena continúa siendo un símbolo de orgullo para la comunidad local y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudocartagena");
        nuevoRegistro.put("nombreEstadio", "Estadio Municipal Cartagonova");
        nuevoRegistro.put("imagenEstadio", "estadiocartagena");
        nuevoRegistro.put("latitud", "37.60963088115913");
        nuevoRegistro.put("longitud", "-0.9959354843878037");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 38);
        nuevoRegistro.put("nombre", "Villarreal B");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Villarreal Club de Fútbol \"B\", comúnmente conocido como Villarreal B, es el equipo filial del Villarreal Club de Fútbol, un club español con sede en la ciudad de Villarreal, en la provincia de Castellón. Fundado en 1999, el Villarreal B compite en la Segunda División B, la tercera categoría del fútbol español. El equipo actúa como un trampolín para jóvenes talentos y jugadores en desarrollo, proporcionándoles experiencia competitiva antes de ascender al primer equipo del Villarreal o buscar oportunidades en otros clubes. El Villarreal B juega sus partidos como local en el Mini Estadi de la Ciudad Deportiva Villarreal CF, ubicado en las afueras de Villarreal. Aunque su principal objetivo es el desarrollo de jugadores, el Villarreal B ha logrado ciertos éxitos en la Segunda División B y ha contribuido significativamente al éxito general del Villarreal Club de Fútbol.");
        nuevoRegistro.put("escudo", "escudovillarrealb");
        nuevoRegistro.put("nombreEstadio", "Estadio de la Cerámica");
        nuevoRegistro.put("imagenEstadio", "estadiovillarreal");
        nuevoRegistro.put("latitud", "39.94410786007454");
        nuevoRegistro.put("longitud", "-0.10349215313618856");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 39);
        nuevoRegistro.put("nombre", "Alcorcón");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "La Agrupación Deportiva Alcorcón, conocida comúnmente como AD Alcorcón, es un club de fútbol español con sede en Alcorcón, en la Comunidad de Madrid. Fundado en 1971, el equipo ha competido en varias divisiones del fútbol español, ascendiendo gradualmente hasta alcanzar la Segunda División, donde ha tenido varias temporadas. El AD Alcorcón juega sus partidos como local en el Estadio Municipal de Santo Domingo, que tiene capacidad para albergar a más de 5,000 espectadores. A lo largo de su historia, el club ha ganado reconocimiento por su estilo de juego competitivo y su capacidad para desafiar a equipos más establecidos. Aunque no ha tenido un éxito destacado a nivel nacional, el AD Alcorcón continúa siendo un símbolo de orgullo para la ciudad de Alcorcón y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoalcorcon");
        nuevoRegistro.put("nombreEstadio", "Estadio Municipal de Santo Domingo");
        nuevoRegistro.put("imagenEstadio", "estadioalcorcon");
        nuevoRegistro.put("latitud", "40.33880192658525");
        nuevoRegistro.put("longitud", "-3.840471401538943");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 40);
        nuevoRegistro.put("nombre", "Albacete");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Albacete Balompié, conocido comúnmente como Albacete, es un club de fútbol español con sede en la ciudad de Albacete, en la comunidad autónoma de Castilla-La Mancha. Fundado en 1940, el equipo ha competido en varias divisiones del fútbol español, incluyendo La Liga y la Segunda División. El Albacete juega sus partidos como local en el Estadio Carlos Belmonte, que tiene capacidad para albergar a más de 17,000 espectadores. A lo largo de su historia, el club ha ganado reconocimiento por su estilo de juego competitivo y su capacidad para desafiar a equipos más grandes. Aunque ha enfrentado desafíos en su historia, el Albacete Balompié continúa siendo un símbolo de orgullo para la ciudad de Albacete y un competidor respetado en el fútbol español.");
        nuevoRegistro.put("escudo", "escudoalbacete");
        nuevoRegistro.put("nombreEstadio", "Estadio Carlos Belmonte");
        nuevoRegistro.put("imagenEstadio", "estadioalbacete");
        nuevoRegistro.put("latitud", "38.98110943432235");
        nuevoRegistro.put("longitud", "-1.8522168137254922");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 41);
        nuevoRegistro.put("nombre", "FC Andorra");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El FC Andorra, también conocido como Futbol Club Andorra, es un club de fútbol situado en el Principado de Andorra. Fundado en 1942, el club ha competido principalmente en las ligas nacionales de Andorra, aunque en temporadas recientes ha buscado expandir su presencia en el fútbol internacional. En 2018, el FC Andorra fue adquirido por el jugador de fútbol Gerard Piqué, quien ha expresado su interés en llevar al club a niveles más altos de competición. Aunque el FC Andorra ha tenido un historial limitado en el fútbol español, su reciente expansión y ambiciones futuras han generado interés y expectativas en la comunidad futbolística. El club juega sus partidos como local en el Estadi Nacional de Andorra, situado en la capital del país, Andorra la Vella.");
        nuevoRegistro.put("escudo", "escudoandorra");
        nuevoRegistro.put("nombreEstadio", "Estadio Nacional de Andorra");
        nuevoRegistro.put("imagenEstadio", "estadioandorra");
        nuevoRegistro.put("latitud", "42.50459705935872");
        nuevoRegistro.put("longitud", "1.517597186508222");
        db.insert("Equipo", null, nuevoRegistro);

        nuevoRegistro = new ContentValues();
        nuevoRegistro.put("id", 42);
        nuevoRegistro.put("nombre", "Amorebieta");
        nuevoRegistro.put("division", 2);
        nuevoRegistro.put("descripcion", "\n" +
                "El Sociedad Deportiva Amorebieta, comúnmente conocido como SD Amorebieta o simplemente Amorebieta, es un club de fútbol español con sede en la localidad de Amorebieta-Etxano, en el País Vasco. Fundado en 1925, el equipo compite actualmente en la Segunda División B, la tercera categoría del fútbol español. El Amorebieta juega sus partidos como local en el Estadio Urritxe, que tiene capacidad para albergar a unos 4.000 espectadores. A lo largo de su historia, el club ha sido un competidor regular en las divisiones regionales y ha tenido apariciones esporádicas en las categorías nacionales. Aunque no ha tenido un éxito destacado a nivel nacional, el SD Amorebieta sigue siendo un símbolo de orgullo para la comunidad local y un lugar donde los aficionados disfrutan del fútbol.");
        nuevoRegistro.put("escudo", "escudoamorebieta");
        nuevoRegistro.put("nombreEstadio", "Estadio Urritxe");
        nuevoRegistro.put("imagenEstadio", "estadioamorebieta");
        nuevoRegistro.put("latitud", "43.221358879908934");
        nuevoRegistro.put("longitud", "-2.7248229339579506");
        db.insert("Equipo", null, nuevoRegistro);

        //Cerramos la base de datos
        db.close();

    }
}