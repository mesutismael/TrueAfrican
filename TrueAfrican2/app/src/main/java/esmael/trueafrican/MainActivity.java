package esmael.trueafrican;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import HelperClasses.ApiAccess;
import HelperClasses.GlobalVariables;
import HelperClasses.JsonHandler;
import HelperClasses.StatusValues;
import Modals.SystemModal;

public class MainActivity extends AppCompatActivity {

    private TextView total_memory;
    private TextView used_memory;
    private TextView free_memory;

    private TextView processor1;
    private TextView processor2;
    private TextView processor3;
    private TextView processor4;
    private GraphView graph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

         graph = (GraphView) findViewById(R.id.graph_cpu);

        total_memory= (TextView)findViewById(R.id.total_memory) ;
        free_memory= (TextView)findViewById(R.id.free_memory) ;
        used_memory= (TextView)findViewById(R.id.used_memory) ;

        processor1= (TextView)findViewById(R.id.cpu_one) ;
        processor2= (TextView)findViewById(R.id.cpu_tw0) ;
        processor3= (TextView)findViewById(R.id.cputhree) ;
        processor4= (TextView)findViewById(R.id.cpufour) ;



        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Refreshing Data", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });



                        try
                        {
                            JSONObject jsonObject= new JSONObject();
                            new GetDataAsyncTask().execute(jsonObject);
                        }catch(Exception e)
                        {
                            Toast.makeText(getApplicationContext(), StatusValues.FAILED_TO_RETRIEVE_DATA.getName(),Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    private class GetDataAsyncTask extends AsyncTask<JSONObject, Integer, String> {
        JsonHandler jsonHandler = new JsonHandler();

        @Override
        protected String doInBackground(JSONObject... params) {
            try {
                JSONObject login_json = params[0];
                String response = new ApiAccess().GetJSONDataFromAPI(GlobalVariables.API_ACCESS_URL);
                return response;
            } catch (Exception exc) {
                Toast.makeText(MainActivity.this, "Error Getting Data, please try again", Toast.LENGTH_LONG).show();
                System.out.println("MainActivity#GetDataAsyncTask#doInBackground exc : " + exc.getMessage());
                exc.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(String result) {
            try {

                if(result!= null)
                {
                    SystemModal systemParams= new JsonHandler().getSysteElementsFromJsonString(result.trim());
                    if(systemParams!= null)
                    {
                        Toast.makeText(getApplicationContext(), StatusValues.DATA_RECEIVED.getName(),Toast.LENGTH_LONG).show();
                       total_memory.setText(systemParams.getMemory().getTotal_memory());
                        free_memory.setText(systemParams.getMemory().getFree_memory());
                        used_memory.setText(systemParams.getMemory().getUsed_memory());

                        processor1.setText(systemParams.getCpu().get(0));
                        processor2.setText(systemParams.getCpu().get(1));
                        processor3.setText(systemParams.getCpu().get(2));
                        processor4.setText(systemParams.getCpu().get(3));
                        Double processorItem1=new Double(systemParams.getCpu().get(0));
                        Double processorItem2=new Double(systemParams.getCpu().get(1));
                        Double processorItem3=new Double(systemParams.getCpu().get(2));
                        Double processorItem4=new Double(systemParams.getCpu().get(3));

                        LineGraphSeries<DataPoint> series = new LineGraphSeries<DataPoint>(new DataPoint[] {

                                new DataPoint(1, processorItem1),
                                new DataPoint(1, processorItem2),
                                new DataPoint(2, processorItem3),
                                new DataPoint(3, processorItem4),

                        });

                        graph.addSeries(series);

                    }else
                    {
                        Toast.makeText(getApplicationContext(), StatusValues.FAILED_ATTEMPT_TO_RETRIEVE_DATA.getName(),Toast.LENGTH_LONG).show();
                    }

                }else
                {
                    Toast.makeText(getApplicationContext(), StatusValues.FAILED_ATTEMPT_TO_RETRIEVE_DATA.getName(),Toast.LENGTH_LONG).show();

                }


            } catch (Exception exc) {

                Toast.makeText(getApplicationContext(), StatusValues.FAILED_ATTEMPT_TO_RETRIEVE_DATA.getName(),Toast.LENGTH_LONG).show();
                exc.printStackTrace();
            }
        }

    }
}
