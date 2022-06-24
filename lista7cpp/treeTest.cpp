#include<iostream>
#include<string>
using namespace std;
#include "tree.hpp"

int main(){
    cout<<"-----------------------------------"<<endl<<"Select the type of tree you'd want to work on: "<<endl<<"1. a tree of integers\n2. a tree of floating point numbers\n3. a tree of strings\n0. exit the program"<<endl<<"-----------------------------------"<<endl;
    int n=0;
    cin>>n;
    bool run = true;
    switch(n) {
        case 1:{
            Tree<int> intTree;
            while(run){
                cout<<endl<<"-----------------------------------"<<endl<<"Select what you'd want to do now."<<endl<<"1. search whether an element is in the tree\n2. insert an element into the tree\n3. delete an element from the tree\n4. draw the whole tree\n0. exit the program."<<endl;
                int x;
                cin>>x;
                int y;
                if (x>0 && x<4){
                    cout<<"type in the element: ";
                    cin>>y;
                }
                switch(x) {
                    case 1:
                        if(intTree.search(y)){cout<<y<<" is in the tree"<<endl;}
                        else {cout<<y<<" is not in the tree"<<endl;}
                        break;
                    case 2:
                        if(intTree.search(y)){cout<<y<<"Warning! "<<y<<" is already in the tree. Adding duplicate."<<endl;}
                        intTree.insert(y);
                        cout<<y<<" has been inserted into the tree"<<endl;
                        break;
                    case 3:
                        if(intTree.search(y)){
                            intTree.del(y);
                            cout<<y<<" has been deleted from the tree."<<endl;
                        }
                        else{cout<<y<<" was not in the tree in the first place"<<endl;}
                        break;
                    case 4:
                        cout<<"printing the whole tree."<<endl;
                        cout<<intTree.draw();
                        break;
                    case 0:
                        cout<<"exiting the program."<<endl;
                        run = false;
                    default:
                        cout<<"wrong number."<<endl;
                        break;
                }
            }
        break;}
        case 2:{
            Tree<double> floatTree;
            while(run){
                cout<<endl<<"-----------------------------------"<<endl<<"Select what you'd want to do now."<<endl<<"1. search whether an element is in the tree\n2. insert an element into the tree\n3. delete an element from the tree\n4. draw the whole tree\n0. exit the program."<<endl;
                int x;
                cin>>x;
                double y;
                if (x>0 && x<4){
                    cout<<"type in the element: ";
                    cin>>y;
                }
                switch(x) {
                    case 1:
                        if(floatTree.search(y)){cout<<y<<" is in the tree"<<endl;}
                        else {cout<<y<<" is not in the tree"<<endl;}
                        break;
                    case 2:
                        if(floatTree.search(y)){cout<<y<<"Warning! "<<y<<" is already in the tree. Adding duplicate."<<endl;}
                        floatTree.insert(y);
                        cout<<y<<" has been inserted into the tree"<<endl;
                        break;
                    case 3:
                        if(floatTree.search(y)){
                            floatTree.del(y);
                            cout<<y<<" has been deleted from the tree."<<endl;
                        }
                        else{cout<<y<<" was not in the tree in the first place"<<endl;}
                        break;
                    case 4:
                        cout<<"printing the whole tree."<<endl;
                        cout<<floatTree.draw();
                        break;
                    case 0:
                        cout<<"exiting the program."<<endl;
                        run = false;
                    default:
                        cout<<"wrong number."<<endl;
                        break;

                }

            }
        break;}
        case 3:{
            Tree<string> strTree;
            while(run){
                cout<<endl<<"-----------------------------------"<<endl<<"Select what you'd want to do now."<<endl<<"1. search whether an element is in the tree\n2. insert an element into the tree\n3. delete an element from the tree\n4. draw the whole tree\n0. exit the program."<<endl;
                int x;
                cin>>x;
                string y;
                if (x>0 && x<4){
                    cout<<"type in the element: ";
                    cin>>y;
                }
                switch(x) {
                    case 1:
                        if(strTree.search(y)){cout<<y<<" is in the tree"<<endl;}
                        else {cout<<y<<" is not in the tree"<<endl;}
                        break;
                    case 2:
                        if(strTree.search(y)){cout<<y<<"Warning! "<<y<<" is already in the tree. Adding duplicate."<<endl;}
                        strTree.insert(y);
                        cout<<y<<" has been inserted into the tree"<<endl;
                        break;
                    case 3:
                        if(strTree.search(y)){
                            strTree.del(y);
                            cout<<y<<" has been deleted from the tree."<<endl;
                        }
                        else{cout<<y<<" was not in the tree in the first place"<<endl;}
                        break;
                    case 4:
                        cout<<"printing the whole tree."<<endl;
                        cout<<strTree.draw();
                        break;
                    case 0:
                        cout<<"exiting the program."<<endl;
                        run = false;
                    default:
                        cout<<"wrong number."<<endl;
                        break;

                }

            }
        break;}
        case 0:
            cout<<"exiting the program"<<endl;
        break;

        default:
            cout<<"wrong number"<<endl;
        break;    
    }
    return 0;
}
