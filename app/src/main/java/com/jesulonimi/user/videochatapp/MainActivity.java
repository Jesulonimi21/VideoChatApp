package com.jesulonimi.user.videochatapp;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.FrameLayout;

import com.opentok.android.OpentokError;
import com.opentok.android.Publisher;
import com.opentok.android.PublisherKit;
import com.opentok.android.Session;
import com.opentok.android.Stream;
import com.opentok.android.Subscriber;

import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

public class MainActivity extends AppCompatActivity implements Session.SessionListener, PublisherKit.PublisherListener {
    public static final String API_KEY = "use your own api key from opentok";
    public static final String TOKEN = "use your own token from opentok";
    public static final String SESSIONID = "use your session id from opentok";
    public static final String LOGTAG = MainActivity.class.getSimpleName();
    private FrameLayout publisherContainer, subscriberContainer;
    private Session session;
    public static final int ALLREQUESTCODE = 1123;
    private Publisher publisher;
    private Subscriber subscriber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        publisherContainer = findViewById(R.id.publisher_container);
        subscriberContainer = findViewById(R.id.subscriber_container);
        requestPermissions();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    @AfterPermissionGranted(ALLREQUESTCODE)
    private void requestPermissions() {
        String per[] = {Manifest.permission.INTERNET, Manifest.permission.CAMERA, Manifest.permission.RECORD_AUDIO};
        if (EasyPermissions.hasPermissions(this, per)) {
            session = new Session.Builder(this, API_KEY, SESSIONID).build();
            session.setSessionListener(this);
            session.connect(TOKEN);
        } else {
            EasyPermissions.requestPermissions(this, "This application needs your Camera and audio", ALLREQUESTCODE, per);
        }
        ;
    }

    @Override
    public void onConnected(Session session) {
        publisher = new Publisher.Builder(this).build();
        publisher.setPublisherListener(this);

        publisherContainer.addView(publisher.getView());
        session.publish(publisher);

    }

    @Override
    public void onDisconnected(Session session) {

    }

    @Override
    public void onStreamReceived(Session session, Stream stream) {
        if (subscriber == null) {
            subscriber = new Subscriber.Builder(this, stream).build();
            session.subscribe(subscriber);
            subscriberContainer.addView(subscriber.getView());
        }
    }

    @Override
    public void onStreamDropped(Session session, Stream stream) {
        if (subscriber != null) {
            subscriber = null;
            subscriberContainer.removeAllViews();
        }
    }

    @Override
    public void onError(Session session, OpentokError opentokError) {

    }


    @Override
    public void onStreamCreated(PublisherKit publisherKit, Stream stream) {

    }


    @Override
    public void onStreamDestroyed(PublisherKit publisherKit, Stream stream) {

    }

    @Override
    public void onError(PublisherKit publisherKit, OpentokError opentokError) {

    }
}
