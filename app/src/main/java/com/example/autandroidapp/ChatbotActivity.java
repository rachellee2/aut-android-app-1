package com.example.autandroidapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.RelativeLayout;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import ai.api.AIConfiguration;
import ai.api.AIDataService;
import ai.api.AIListener;
import ai.api.android.AIService;
import ai.api.model.AIRequest;
import ai.api.model.Result;
import ai.api.AIServiceException;
import ai.api.model.AIResponse;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class ChatbotActivity extends AppCompatActivity implements AIListener {

    private AIService aiService;
    RecyclerView recyclerView;
    EditText editText; //allows for user messages to be edited
    RelativeLayout addButton; //relative layout can be changed later
    DatabaseReference databaseReference; //firebase reference
    FirebaseRecyclerAdapter<ChatbotMessage,ChatbotRecycler> fRAdapter;
    Boolean flag = true;


    public boolean onCreateOptionsMenu(Menu menu)
    {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.action_home:
                Intent homePageIntent= new Intent(this, MainActivity.class);
                this.startActivity(homePageIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatbot);
        Intent intent = getIntent();

        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},1); //AUDIO

        //Assignment
        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        editText = (EditText)findViewById(R.id.editText);
        addButton = (RelativeLayout)findViewById(R.id.addBtn);

        //Setting layout for the Recycle Viewer
        recyclerView.setHasFixedSize(true);
        final LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(linearLayoutManager);

        //Firebase connection and AI to Database configuration
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        databaseReference.keepSynced(true);
        Query query = databaseReference.child("chats").limitToLast(50); //limit msgs shown to the last 50

        //Binding a Query to a RecyclerView
        FirebaseRecyclerOptions<ChatbotMessage> options =
                new FirebaseRecyclerOptions.Builder<ChatbotMessage>().setQuery(query, ChatbotMessage.class).build();


        //Dialog Flow connection
        final ai.api.android.AIConfiguration aiconfig = new ai.api.android.AIConfiguration("acd43e00b5dc415685c033294a283b7a",
                AIConfiguration.SupportedLanguages.English, ai.api.android.AIConfiguration.RecognitionEngine.System);

        //ailistener
        aiService = AIService.getService(this,aiconfig);
        aiService.setListener(this);

        final AIDataService aiDataService = new AIDataService(aiconfig);
        final AIRequest aiRequest = new AIRequest();

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message = editText.getText().toString().trim();

                if (!message.equals("")) {

                    ChatbotMessage chatMessage = new ChatbotMessage(message, "user");
                    databaseReference.child("chat").push().setValue(chatMessage);

                    aiRequest.setQuery(message);
                    new AsyncTask<AIRequest,Void,AIResponse>(){

                        @Override
                        protected AIResponse doInBackground(AIRequest... aiRequests) {
                            final AIRequest request = aiRequests[0];
                            try {
                                final AIResponse response = aiDataService.request(aiRequest);
                                return response;
                            } catch (AIServiceException e) {
                            }
                            return null;
                        }
                        @Override
                        protected void onPostExecute(AIResponse response) {
                            if (response != null) {

                                Result result = response.getResult();
                                String reply = result.getFulfillment().getSpeech();
                                ChatbotMessage chatMessage = new ChatbotMessage(reply, "bot");
                                databaseReference.child("chat").push().setValue(chatMessage);
                            }
                        }
                    }.execute(aiRequest);
                }
                else {
                    aiService.startListening();
                }
                editText.setText("");
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                ImageView fab_img = (ImageView)findViewById(R.id.fab_img);
                Bitmap img = BitmapFactory.decodeResource(getResources(),R.drawable.ic_menu_send);
                Bitmap img1 = BitmapFactory.decodeResource(getResources(),R.drawable.ic_menu_mic);
                if (s.toString().trim().length()!=0 && flag){
                    ImageViewAnimatedChange(ChatbotActivity.this,fab_img,img);
                    flag=false;
                }
                else if (s.toString().trim().length()==0){
                    ImageViewAnimatedChange(ChatbotActivity.this,fab_img,img1);
                    flag=true;
                }
            }

            @Override
            public void afterTextChanged(Editable s) { }
        });

        /**fRAdapter = new FirebaseListAdapter<ChatbotMessage>(ChatbotMessage.class,R.layout.activity_chatbot_msg,ChatbotRecycler.class,databaseReference.child("chat")) {
            @Override
            protected void populateView(ChatbotRecycler viewHolder, ChatbotMessage model, int position)
            {
                    if (model.getMsgUser().equals("user")) {
                        viewHolder.rightText.setText(model.getMsgText());

                        viewHolder.rightText.setVisibility(View.VISIBLE);
                        viewHolder.leftText.setVisibility(View.GONE);
                    }
                    else {
                        viewHolder.leftText.setText(model.getMsgText());

                        viewHolder.rightText.setVisibility(View.GONE);
                        viewHolder.leftText.setVisibility(View.VISIBLE);
                    }
            }
        };*/

            //NEED TO SET MSG LAYOUT? R.layout.activity_chatbot_msg
        fRAdapter = new FirebaseRecyclerAdapter<ChatbotMessage, ChatbotRecycler>(options)
        {
            @Override
            protected void onBindViewHolder(ChatbotRecycler viewHolder, int position, ChatbotMessage model) {
                if (model.getMsgUser().equals("user")) {
                    viewHolder.rightText.setText(model.getMsgText());
                    viewHolder.rightText.setVisibility(View.VISIBLE);
                    viewHolder.leftText.setVisibility(View.GONE);
                } else {
                    viewHolder.leftText.setText(model.getMsgText());
                    viewHolder.rightText.setVisibility(View.GONE);
                    viewHolder.leftText.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public ChatbotRecycler onCreateViewHolder(ViewGroup parent, int viewType) {
                //create new instance of viewholder
                View view = findViewById(R.id.recyclerView);
                return new ChatbotRecycler(view);
            }

            //public void onDataChange() available
            //public void onError(DatabaseError e) available*/
        };

        fRAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onItemRangeInserted(int positionStart, int itemCount) {
                super.onItemRangeInserted(positionStart, itemCount);
                int msgCount = fRAdapter.getItemCount();
                int lastVisiblePosition = linearLayoutManager.findLastCompletelyVisibleItemPosition();
                if (lastVisiblePosition == -1 ||
                        (positionStart >= (msgCount - 1) &&
                                lastVisiblePosition == (positionStart - 1))) {
                    recyclerView.scrollToPosition(positionStart);
                }
            }
        });
        recyclerView.setAdapter(fRAdapter);
    }

    @Override
    protected void onStart()
    {
        super.onStart();
        fRAdapter.startListening();
    }

    @Override
    protected void onStop()
    {
        super.onStop();
        fRAdapter.stopListening();
    }

    public void ImageViewAnimatedChange(Context c, final ImageView v, final Bitmap new_image) {
        final Animation anim_out = AnimationUtils.loadAnimation(c, R.anim.zoom_out);
        final Animation anim_in  = AnimationUtils.loadAnimation(c, R.anim.zoom_in);
        anim_out.setAnimationListener(new Animation.AnimationListener()
        {
            @Override public void onAnimationStart(Animation animation) {}
            @Override public void onAnimationRepeat(Animation animation) {}
            @Override public void onAnimationEnd(Animation animation)
            {
                v.setImageBitmap(new_image);
                anim_in.setAnimationListener(new Animation.AnimationListener() {
                    @Override public void onAnimationStart(Animation animation) {}
                    @Override public void onAnimationRepeat(Animation animation) {}
                    @Override public void onAnimationEnd(Animation animation) {}
                });
                v.startAnimation(anim_in);
            }
        });
        v.startAnimation(anim_out);
    }

    @Override
    //Main function used for AIListener implementation
    public void onResult(ai.api.model.AIResponse response) {
        Result result = response.getResult();
        String message = result.getResolvedQuery();
        ChatbotMessage chatMessage0 = new ChatbotMessage(message, "user");
        databaseReference.child("chat").push().setValue(chatMessage0);

        String reply = result.getFulfillment().getSpeech();
        ChatbotMessage chatMessage = new ChatbotMessage(reply, "bot");
        databaseReference.child("chat").push().setValue(chatMessage);
    }

    //ALL Super functions required for implementation
    public void onError(ai.api.model.AIError error){}
    public void onAudioLevel(float level){}
    public void onListeningStarted(){}
    public void onListeningCanceled(){}
    public void onListeningFinished(){}
}
