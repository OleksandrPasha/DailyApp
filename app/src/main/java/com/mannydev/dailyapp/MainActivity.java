package com.mannydev.dailyapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.ads.InterstitialAd;
import com.google.android.gms.ads.MobileAds;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mannydev.dailyapp.controller.AnekdotAPI;
import com.mannydev.dailyapp.controller.Controller;
import com.mannydev.dailyapp.controller.HoroscopeAPI;
import com.mannydev.dailyapp.controller.KursValutAPI;
import com.mannydev.dailyapp.controller.MoonDayAPI;
import com.mannydev.dailyapp.controller.WeatherAPI;
import com.mannydev.dailyapp.model.anekdot.Anekdot;
import com.mannydev.dailyapp.model.anekdot.Anekdots;
import com.mannydev.dailyapp.model.horoscope.Horo;
import com.mannydev.dailyapp.model.kursvalut.KursValut;
import com.mannydev.dailyapp.model.moonday.MoonDay;
import com.mannydev.dailyapp.model.weather.Weather;
import com.mannydev.dailyapp.model.weather.WeatherWidget;
import com.mannydev.dailyapp.view.AnekdotAdapter;
import com.mannydev.dailyapp.view.WeatherAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final String TAG = "myLogs";
    private static final String WEATHER_KEY = "da34a1770ab7804a79a24144a22414d3";
    private static final String APP_CACHE = "cache";
    private static final String USER_NAME = "userName";
    private static final String ZODIAC = "zodiac";
    private static final String KURS_VALUT = "kurs";
    private static final String CITY = "location";
    private static final String WEATHER = "weather";
    private static final String HOROSCOPE = "horoscope";
    private static final String MOON_DAY = "moonDay";
    private static final String ANEKDOTS = "anekdots";

    @BindView(R.id.btnSettings)
    Button btnSettings;
    @BindView(R.id.txtHoroscopeTitle)
    TextView txtHoroscopeTitle;
    @BindView(R.id.txtHoroscope)
    TextView txtHoroscope;
    @BindView(R.id.lvWeather)
    ListView lvWeather;
    @BindView(R.id.txtUSD)
    TextView txtUSD;
    @BindView(R.id.txtRUB)
    TextView txtRUB;
    @BindView(R.id.txtEUR)
    TextView txtEUR;
    @BindView(R.id.layout_Kurs_today)
    LinearLayout layoutKursToday;
    @BindView(R.id.layout_weather)
    LinearLayout layoutWeather;
    @BindView(R.id.textView5)
    TextView textView5;
    @BindView(R.id.lvAnekdoti)
    ListView lvAnekdoti;
    @BindView(R.id.layoutAnekdoti)
    LinearLayout layoutAnekdoti;

    private SharedPreferences appCache;
    private Weather weather;
    private WeatherWidget weatherWidget;
    private Horo horo;
    private MoonDay moonDay;
    private KursValut kursValut;
    private ArrayList<String> moonDaysInfo;
    private ArrayList<Anekdot>anekdots;
    private String name;
    private String city;
    private String zodiac;

    private InterstitialAd mInterstitialAd;
    AnekdotAdapter anekdotAdapter;
    WeatherAdapter weatherAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-8078146669032188~5186526195");


        appCache = getSharedPreferences(APP_CACHE, MODE_PRIVATE);
        initMoonDays();

        anekdotAdapter = new AnekdotAdapter();
        weatherAdapter = new WeatherAdapter(this);
        lvWeather.setAdapter(weatherAdapter);
        lvAnekdoti.setAdapter(anekdotAdapter);


        if (!appCache.contains(USER_NAME)) {
            Intent intent = new Intent(this, FirstActivity.class);
            startActivity(intent);
        } else {
            getMainFromCache();
            getMoonDayFromCache();
            txtHoroscopeTitle.setText(name +", Ваш гороскоп на сегодня:");
            initComponents();
            }

        btnSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, FirstActivity.class);
                startActivity(intent);
            }
        });

    }

    private void getMainFromCache() {
        name = appCache.getString(USER_NAME, null);
        city = appCache.getString(CITY, null);
        zodiac = appCache.getString(ZODIAC, null);
    }

    private void initComponents() {
        getKursValut();
        getMoonDay();
        getWeather(city);
        getHoroscope();
        getAnekdots();

    }

    private void getAnekdots() {
        AnekdotAPI anekdotAPI = Controller.getAnekdotsAPI();
        anekdotAPI.getAnekdots().enqueue(new Callback<ArrayList<Anekdot>>() {
            @Override
            public void onResponse(Call<ArrayList<Anekdot>> call, Response<ArrayList<Anekdot>> response) {
                anekdots = response.body();
                if(response.isSuccessful()){
                    Anekdots jokes = new Anekdots();
                    jokes.setList(anekdots);
                    objectToCache(jokes, ANEKDOTS);
                    anekdotAdapter.setData(anekdots);
                    anekdotAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Anekdot>> call, Throwable t) {
                getAnekdotsFromCache();
            }
        });
    }

    private void getAnekdotsFromCache() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        Anekdots jokes = gson.fromJson(appCache.getString(ANEKDOTS, null), Anekdots.class);
        anekdots = jokes.getList();
        anekdotAdapter.setData(anekdots);
        anekdotAdapter.notifyDataSetChanged();
    }

    private void getMoonDay() {
        MoonDayAPI moonDayAPI = Controller.getMoonDayAPI();
        moonDayAPI.getMoonDay().enqueue(new Callback<MoonDay>() {
            @Override
            public void onResponse(Call<MoonDay> call, Response<MoonDay> response) {
                moonDay = response.body();
                if(response.isSuccessful()){
                    Log.d(TAG,"Moon Day is :"+ moonDay.getMoonday());
                    objectToCache(moonDay, MOON_DAY);
                }
            }
            @Override
            public void onFailure(Call<MoonDay> call, Throwable t) {
                getMoonDayFromCache();
            }
        });

    }

    private void getMoonDayFromCache() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        moonDay = gson.fromJson(appCache.getString(MOON_DAY, null), MoonDay.class);
    }

    private void getHoroscope() {
        HoroscopeAPI horoscopeApi = Controller.getHoroApi();
        horo = new Horo();
        horoscopeApi.getHoro().enqueue(new Callback<Horo>() {
            @Override
            public void onResponse(Call<Horo> call, Response<Horo> response) {

                horo = response.body();

                if (response.isSuccessful()) {
                    objectToCache(horo, HOROSCOPE);
                    switch (zodiac) {
                        case "Овен":
                            txtHoroscope.setText(horo.getAries().getToday().replace("\n", ""));
                            break;
                        case "Телец":
                            txtHoroscope.setText(horo.getTaurus().getToday().replace("\n", ""));
                            break;
                        case "Близнецы":
                            txtHoroscope.setText(horo.getGemini().getToday().replace("\n", ""));
                            break;
                        case "Рак":
                            txtHoroscope.setText(horo.getCancer().getToday().replace("\n", ""));
                            break;
                        case "Лев":
                            txtHoroscope.setText(horo.getLeo().getToday().replace("\n", ""));
                            break;
                        case "Дева":
                            txtHoroscope.setText(horo.getVirgo().getToday().replace("\n", ""));
                            break;
                        case "Весы":
                            txtHoroscope.setText(horo.getLibra().getToday().replace("\n", ""));
                            break;
                        case "Скорпион":
                            txtHoroscope.setText(horo.getScorpio().getToday().replace("\n", ""));
                            break;
                        case "Стрелец":
                            txtHoroscope.setText(horo.getSagittarius().getToday().replace("\n", ""));
                            break;
                        case "Козерог":
                            txtHoroscope.setText(horo.getCapricorn().getToday().replace("\n", ""));
                            break;
                        case "Водолей":
                            txtHoroscope.setText(horo.getAquarius().getToday().replace("\n", ""));
                            break;
                        case "Рыбы":
                            txtHoroscope.setText(horo.getPisces().getToday().replace("\n", ""));
                            break;
                    }

                }
            }

            @Override
            public void onFailure(Call<Horo> call, Throwable t) {
                getHoroscopeFromCache();
            }
        });

    }

    private void getHoroscopeFromCache() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        horo = gson.fromJson(appCache.getString(HOROSCOPE, null), Horo.class);
        switch (zodiac) {
            case "Овен":
                txtHoroscope.setText(horo.getAries().getToday().replace("\n", ""));
                break;
            case "Телец":
                txtHoroscope.setText(horo.getTaurus().getToday().replace("\n", ""));
                break;
            case "Близнецы":
                txtHoroscope.setText(horo.getGemini().getToday().replace("\n", ""));
                break;
            case "Рак":
                txtHoroscope.setText(horo.getCancer().getToday().replace("\n", ""));
                break;
            case "Лев":
                txtHoroscope.setText(horo.getLeo().getToday().replace("\n", ""));
                break;
            case "Дева":
                txtHoroscope.setText(horo.getVirgo().getToday().replace("\n", ""));
                break;
            case "Весы":
                txtHoroscope.setText(horo.getLibra().getToday().replace("\n", ""));
                break;
            case "Скорпион":
                txtHoroscope.setText(horo.getScorpio().getToday().replace("\n", ""));
                break;
            case "Стрелец":
                txtHoroscope.setText(horo.getSagittarius().getToday().replace("\n", ""));
                break;
            case "Козерог":
                txtHoroscope.setText(horo.getCapricorn().getToday().replace("\n", ""));
                break;
            case "Водолей":
                txtHoroscope.setText(horo.getAquarius().getToday().replace("\n", ""));
                break;
            case "Рыбы":
                txtHoroscope.setText(horo.getPisces().getToday().replace("\n", ""));
                break;
        }

    }

    private void getWeather(String cityName) {
        WeatherAPI weatherAPI = Controller.getWeatherApi();
        weather = new Weather();
        weatherAPI.getWeather(cityName, "metric", WEATHER_KEY).enqueue(new Callback<Weather>() {

            @Override
            public void onResponse(Call<Weather> call, Response<Weather> response) {

                weather = response.body();
                if (response.isSuccessful()) {
                    String temp = "" + Math.round(weather.getMain().getTemp()) + "°";
                    String wind = "Вет. : " + String.valueOf(weather.getWind().getSpeed()) + " м/с";
                    String pressure = "Давл. : " + String.valueOf(weather.getMain().getPressure()) + " hpa";
                    String aqua = "Влажн. : " + String.valueOf(weather.getMain().getHumidity()) + " %";
                    String city = weather.getName() + ", " + weather.getSys().getCountry();
                    String icon = weather.getWeather().get(0).getIcon();
                    Log.v("CITY", city);
                    weatherWidget = new WeatherWidget(temp,wind,pressure,aqua,icon,city);
                    objectToCache(weatherWidget, WEATHER);
                    ArrayList<WeatherWidget> list = new ArrayList<WeatherWidget>();
                    list.add(weatherWidget);
                    weatherAdapter.setData(list,moonDay,moonDaysInfo.get(moonDay.getMoonday()-1));
                    weatherAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<Weather> call, Throwable t) {
                getWeatherFromCache();
            }
        });
    }

    private void getWeatherFromCache() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        weatherWidget = gson.fromJson(appCache.getString(WEATHER, null), WeatherWidget.class);
        ArrayList<WeatherWidget> list = new ArrayList<WeatherWidget>();
        list.add(weatherWidget);
        weatherAdapter.setData(list,moonDay,moonDaysInfo.get(moonDay.getMoonday()-1));
        weatherAdapter.notifyDataSetChanged();
    }

    public void getKursValut() {
        KursValutAPI kursValutAPI = Controller.getKursApi();
        kursValut = new KursValut();
        kursValutAPI.getKurs().enqueue(new Callback<KursValut>() {
            @Override
            public void onResponse(Call<KursValut> call, Response<KursValut> response) {
                kursValut = response.body();
                if (response.isSuccessful()) {
                    objectToCache(kursValut, KURS_VALUT);
                    txtUSD.setText(txtUSD.getText() + "\n" + kursValut.getUSD().getNbu().getBuy());
                    txtRUB.setText(txtRUB.getText() + "\n" + kursValut.getRUB().getNbu().getBuy());
                    txtEUR.setText(txtEUR.getText() + "\n" + kursValut.getEUR().getNbu().getBuy());
                }
            }

            @Override
            public void onFailure(Call<KursValut> call, Throwable t) {
                getKursValutFromCache();
            }
        });
    }

    private void getKursValutFromCache() {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        kursValut = gson.fromJson(appCache.getString(KURS_VALUT, null), KursValut.class);
        txtUSD.setText(txtUSD.getText() + "\n" + kursValut.getUSD().getNbu().getBuy());
        txtRUB.setText(txtRUB.getText() + "\n" + kursValut.getRUB().getNbu().getBuy());
        txtEUR.setText(txtEUR.getText() + "\n" + kursValut.getEUR().getNbu().getBuy());
    }

    private void objectToCache(Object object, String label) {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        String s = gson.toJson(object);
        SharedPreferences.Editor editor = appCache.edit();
        editor.putString(label, s);
        editor.apply();
    }

    //Проверка устройства на наличия связи с интернетом
    private static boolean hasConnection(final Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if (wifiInfo != null && wifiInfo.isConnected()) {
            return true;
        }
        wifiInfo = cm.getActiveNetworkInfo();
        return wifiInfo != null && wifiInfo.isConnected();
    }

    private void initMoonDays() {
        moonDaysInfo = new ArrayList<>();
        moonDaysInfo.add("Первый лунный день сегодня — день закладки фундамента на весь лунный месяц. Символы: свеча, лампада, светильник, жертвенник Гекаты. От направления мыслей в этот день зависит весь лунный месяц. Именно поэтому в первый лунный день нужно думать о будущем, но не о далеком и абстрактном, а о реальном и ближайшем. Подумайте, чтобы вы хотели реализовать в текущий лунный месяц.");
        moonDaysInfo.add("Сегодня 2 лунный день, время начать осуществлять задуманное. Символом дня является рог изобилия В этот день уже можно начинать дела, которые вы запланировали на этот лунный месяц. Главное не сомневаться в исполнении задуманного и идти вперед, не оглядываясь! Сейчас организм набирается сил и готовит вас к осуществлению задуманного, еще чуть-чуть и вас будет не остановить!");
        moonDaysInfo.add("Сегодня, в 3 лунный день луна уже становится видна на небе — она как бы дает понять, что ваши планы, задуманные в первые дни лунного месяца, уже начали реализоваться. Сегодня вы можете почувствовать в себе некоторую долю агрессии и напористости — сегодня очень много энергии, которая дана для того, чтобы начать воплощать свои планы в реальность, иначе сила не найдет выхода и только навредит.");
        moonDaysInfo.add("Лунный день сегодня довольно пассивный. Если вчера нам нужно было проявлять активность, то сегодня лучше провести день пассивно и заняться домашними делами. В идеале день нужно провести в уединении. Сегодня отлично удаются медитации, сосредоточение на духовном, прослушивание ведических лекций.");
        moonDaysInfo.add("Символом 5 лунного дня сегодня является единорог, который отвечает за верность своим принципам, взглядам на мир. Сегодня нужно быть сильным и верным себе, никто и ничто не должно вызывать сомнения в правильности вашего жизненного пути. В противном случае, вы можете сбиться с верного пути и пойти по чьей-то вытоптанной тропинке, которая не приведет к вас к желаемому. Сегодня все меняется, как и наша внутренняя сущность.");
        moonDaysInfo.add("6 лунные сутки сегодня — благоприятный период. Нужно проводить его по возможности в спокойной обстановке, и тогда к вам может прийти вдохновение или информация о давно интересующем вас вопросе. То, что было скрыто от понимания, может быть понято сегодня. В 6 лунный день приходит очень много информации, и чтобы принять именно ту информацию, которая необходима, нужно сохранять спокойствие ума. ");
        moonDaysInfo.add("Сегодня 7 лунный день — день работы с вербальной информацией. Речь сегодня имеет особенное значение: все произнесенное сегодня имеет вероятность воплощения в материальном мире. Поэтому лучше говорить и думать о том, чего бы вы хотели. Ни в коем случае не желайте никому зла: «не плюй в колодец, пригодится воды напиться». Все сделанное нами в этот период возвращается к нам в полной мере.");
        moonDaysInfo.add("Символом 8 лунного дня является феникс. Энергетика луны сегодня такова, что лучше не заниматься действиями, на которые уходит много сил. Энергию дня стоит направить на внутреннюю трансформацию, очищение. В 8 лунный день начинается новая фаза лунного месяца. Это довольно непростой день, когда во всем живом происходят глубинная трансформация. Позвольте себе перейти на новый уровень и отбросьте всё ненужное.");
        moonDaysInfo.add("Символ 9 лунного дня сегодня — летучая мышь. Первый «темный» лунный день в месяце. В этот день теневая сторона человека выходит на первый план, мир обретает угрожающие оттенки. Чем больше негатива скопилось в вас, тем более опасен этот день для вас. В этот день нужно стараться не допускать дурных мыслей, не идти на поводу у страха и мрачных мыслей.");
        moonDaysInfo.add("В 10 лунный день сегодня уже нет напряжения от предыдущего дня. Можно назвать этот день днем отдыха. Сегодня наш организм набирает силы и энергию. Отличный день для того, чтобы сходить в баню, заняться выполнением тяжелой работы и бизнесом. В 10 лунный день Луна готовит нас к 11 лунному дню — дню Экадаши, когда у нас есть шанс очиститься духовно и физически. ");
        moonDaysInfo.add("11 лунный день сегодня — день пробуждения кундалини, один из самых мощных энергетических дней в лунном месяце, день экадаши. Символ — корона, лабиринт. Сила этого дня способна помочь человеку преобразиться до неузнаваемости, если только он захочет ею воспользоваться. Этот день ни в коем случае нельзя просыпать — каждая его минута должна быть потрачена с пользой.");
        moonDaysInfo.add("Лунный день сегодня — день милосердия и сострадания, самопознания и единения с вселенной. Сегодня нежелательна чрезмерная активность, пусть все идет своим чередом — медленно, но верно. Не допускайте спешки и импульсивности. Посвятите этот день молитве, сходите в храм, на природу. Вообще, подойдет любое место, в котором вы ощущаете свою связь со всем миром, с божественной сущности, которая всегда находит внутри нас.");
        moonDaysInfo.add("Лунный день сегодня — приятный, отражающий результаты того, как вы провели предыдущие лунные дни. Если вы следовали рекомендациям, то сегодня ваши дела будут идти как колесо, катящееся с горы. 13 лунные сутки обладают таинственной энергетикой: сегодня вы можете узнать что-то необычное и неординарное: то, что так или иначе откроет вам тайну.");
        moonDaysInfo.add("Символ лунного дня сегодня: труба. 14 лунный день — один из наиболее мощных энергетических дней в этом месяце, последний день перед наступлением полнолуния, последняя возможность закончить подкорректировать направление своих дел. Все, что мы делаем во время нарастающей луны, имеет очень важное значения для воплощения задумок в ближайшем будущем.");
        moonDaysInfo.add("Лунный день сегодня в большинстве случае является полнолунием (см. сколько длится полнолуние). 15 лунный день — это очень важное время, когда проявление вашего внутреннего я увеличивается в несколько раз — как плохие, так и хорошие черты выходят наружу.");
        moonDaysInfo.add("Символ 16 лунного дня — бабочка. Сегодня очень благоприятный день, который следует провести по возможности пассивно. Конечно, если у вас есть любимые дела, выполнения которых дается вам без труда, и доставляют только удовольствие, такими делами можно заниматься постоянно! Сегодня спадает напряженность, исчезает суматоха и начинается отдых.");
        moonDaysInfo.add("Символом 17 лунного дня является виноградная гроздь. Этот день имеет связь с Весами. День Шакти. Энергия лунных суток идеально подходит для заключения брака. В 17 лунный день следует быть аккуратным, так как женская энергия может выходить в низком ее проявлении — буйстве, неконтролируемых эмоциях. Лунный день несет в себе довольно противоречивую энергию.");
        moonDaysInfo.add("Символом 18 лунного дня является зеркало, а девизом можно смело назвать «Ничто не случайно». Действительно, в этот лунный день ничто не происходит случайно. Мир становится в буквальном смысле вашим отражением — все, плохое или хорошее, что случается в этот день, есть в вашем сознании. Если кто-то обидел вас или позавидовал вам — не обижайтесь, это значит что вы способны на такие же чувства.");
        moonDaysInfo.add("Символом 19 лунного дня является паук. Это довольно опасный период, он считается одним из самых сложных в лунном месяце. Часто в это время достаточно одного неосторожного слова или взгляда для развития скандала. День является проверкой вашей стойкости, жизненных ценностей и достижений. Слабые в этот день становятся еще слабее, а сильные — сильнее, происходит своеобразный тест на выдержку и реакцию на трудности.");
        moonDaysInfo.add("Символом 20 лунного дня является орел. День имеет одну интересную особенность, которая может помочь вам совершить большой скачок вверх и буквально взлететь: если вы сможете абстрагироваться от занимающих вас проблем и мыслей, сможете посмотреть на ситуацию и понять взаимосвязь и суть проблем, у вас получится «перелететь» эти проблемы и они обязательно решатся в ближайшее время, если вы приложите к этому усилия.");
        moonDaysInfo.add("Символами 21 лунного дня являются лошадь, колесница, Пегас. Это один из самых активных лунных дней в лунном месяце! Энергии в этот лунный день более чем достаточно, но в какое русло она уйдет, зависит только от вас. В гармоничном варианте вы сможете сделать все, на что у вас давно не хватало сил или творческого вдохновения. Не даром символами этих лунных дней считаются лошадь, пегас и колесница — при правильной растрате энергии ваши дела помчатся словно колесница.");
        moonDaysInfo.add("Символом 22 лунного дня является Ганеша — бог мудрости и благополучия, один из наиболее почитаемых в мире богов. Он считается богом изобилия и процветания и дает разум и поддержку для принятия верных решений. Этот день  — день получения знания, духовного учения, передачи сакральных знаний. Вся информация в этот день отлично воспринимается, путешествия благоприятны, а выполнение мистических практик приносит хороший результат.");
        moonDaysInfo.add("23 лунный день — день тяжелый, полный обольщений и необузданных сил. Он насыщен очень агрессивной энергией, которая буквально «давит» на нас изнутри и мощным напором ударяет по нашей оболочке. В этот день желательно чем-нибудь заниматься, быть активным, стараясь хранить внутреннее спокойствие. Старайтесь избавляться от страха, не поддаваться на провокации и соблазну сделать что-то назло.");
        moonDaysInfo.add("Символами 24 лунного дня являются Шива, медведь, гора и фаллос. Этот день имеет связь с мужской половой энергией. Уже нет вчерашней агрессии и мощного потока неконтролируемой энергии — день довольно приятный и спокойный. Сегодня люди относятся к друг другу сердечно — после вчерашнего дня чувствуется усталость, исчезает желание конфликтовать. В 24 лунный день лучше не нагружать себя рутинной работой.");
        moonDaysInfo.add("Символы 25 лунного дня: черепаха, раковина, урна, сосуды с живой и мертвой водой. Самый подходящий день для размышлений о духовности. Активные действия сегодня не рекомендуются, следует смотреть вглубь своей внутренней сущности. Если вам мешает жить какая-то проблема, попробуйте взглянуть на нее свысока, возможно, ваша интуиция подскажет как ее решить.");
        moonDaysInfo.add("26 лунный день неблагоприятен по своей энергетике, сегодня не стоит начинать новых дел и предпринимать что-то важное, так как все это будет «коту под хвост». У вас может возникнуть желание похвастаться, показать свое превосходство и преувеличить реальные достижения. Будет хорошо, если вы сможете смотреть на себя и окружающих людей такими, как они есть — без масок и приукрашиваний.");
        moonDaysInfo.add("Символ 27 лунного дня сегодня — трезубец (Нептун). День глубоко интуитивный, медитативный. Интуиция стоит на первом месте — она ваш гуру, лучший советчик. Старайтесь по возможности меньше контактировать с внешним миром, и больше с внутренним. Телевизор, телефон радио, и даже интернет (!) сегодня лучше отложить в сторонку, и использовать минимально. Конечно, вполне допустимо общаться с хорошими друзьями.");
        moonDaysInfo.add("Символами 28 лунного дня сегодня являются лотос и карма. Хороший день с благотворной энергетикой, день созидания. Сегодня полезно заниматься домашними делами и не нагружать себя тяжелыми задачами. Бурная деятельность нежелательна, однако хорошо пойдут такие дела как покупка дома или ремонт, ухаживание за растениями. В 28 лунном дне присутствует символ Будды — познания высшего, обретения мудрости и ви́дения.");
        moonDaysInfo.add("Сегодня 29 лунный день — день в противоположность 28 лунному дню, опасный. В этот день энергия луны настолько мощна, что мало кому под силу с ней справиться. Резко возрастает вероятность конфликта, ссоры. Слова, сказанные в ваш адрес, вы можете воспринять неверно, и вместо обычной реакции на шутку или безобидное слово, обидеться .Если вовремя не задуматься о происходящем и позволить произойти конфликту, мысли о мести будут одолевать вас или вашего «врага» еще долгое время. ");
        moonDaysInfo.add("Cегодня 30 лунный день бывает не в каждом месяце, а если и бывает, то длится недолго, иногда даже меньше часа. Несмотря на это, это отдельный лунный день, который несет в себе особую энергетику. Это время хорошо наполнить творчеством и провести его в одиночестве. Лунный месяц закончился, теперь нужно набираться сил для свершений в новый лунный месяц.");

    }


}
