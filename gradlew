import 'package:flutter/material.dart';
import 'package:test_flutter_app/pages/home.dart';

class AuthPage extends StatefulWidget {
  @override
  State<StatefulWidget> createState() {
    // TODO: implement createState
    return AuthPageState();
  }

}

class AuthPageState extends State<AuthPage>{
  String _email;
  String _password;
  Widget build(BuildContext context) {
    return Scaffold(
        appBar: AppBar(
          title: Text("Welcome"),
        ),

        body:
        Container(
          margin: EdgeInsets.all(10.0),
          child:  ListView(
            children: <Widget>[
              TextField(
                decoration: InputDecoration(labelText: "email"),
                keyboardType: TextInputType.emailAddress,
                onChanged: (value){
                  setState(() {
                    _email=value;
                  });
                },
              ),
              TextField(
                decoration: InputDecoration(labelText: "password"),
                obscureText: true,
                onChanged: (value){
                  setState(() {
                    _password=value;
                  });
                },
              ),
            SwitchListTile(value:true,onChanged((value){}),title:"Accept terms"),
            SizedBox(height: 10.0,),
              RaisedButton(
                color: Theme.of(context).primaryColor,
                  onPressed: () {
                  print(_email);
                  print(_password);
                    Navigator.pushReplacementNamed(context, '/product');
                  },
                  child: Text("login"),
                ),

            ],
          ),
        )



      );
  }
}
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             