#include<iostream>
#include<string>
#include <sstream>
using namespace std;
#pragma once
template<typename T>
class TreeElement {
    public:
        T value;
        TreeElement<T>* left;
        TreeElement<T>* right;
        TreeElement<T>* parent;
        TreeElement(T value){
            this->value = value;
            this->left = NULL;
            this->right = NULL;
            this->parent = NULL;
        }
        TreeElement(){
            this->left = NULL;
            this->right = NULL;
            this->parent = NULL;
        }
};

template<typename T>
class Tree {
    private:
        TreeElement<T>* root;
        void ins(T element){
            TreeElement<T>* z = new TreeElement<T> (element);
            TreeElement<T>* y = NULL;
            TreeElement<T>* x = root;
            while (x!=NULL){
                y=x;
                if (element<x->value) {x = x->left;}
                else {x = x->right;}
            }
            z->parent = y;
            if (y==NULL) {root = z;}
            else if (element<y->value) {y->left = z;}
            else {y->right = z;}
        }
        bool isElem(T element, TreeElement<T>* x){
            if (x==NULL) {return false;}
            if (element==x->value) {return true;}
            if (element<x->value) {return isElem(element,x->left);}
            else {return isElem(element,x->right);}
        }
        TreeElement<T>* Tminimum (TreeElement<T>* x){
            while (x->left!=NULL){x=x->left;}
            return x;
        }
        string toS(TreeElement<T>* x){
            if (x!=NULL) {
                std::string xv;
                std::stringstream strstr;
                strstr<<x->value;
                xv = strstr.str();
                return "("+xv+" : "+toS(x->left)+" : "+toS(x->right) + ")";}
            return "()";                    
        }
        TreeElement<T>* TDelete (TreeElement<T>* r, T z){
            if (r==NULL) {return r;}
            if (z<r->value) {r->left = TDelete(r->left,z);}
            else if (z>r->value) {r->right = TDelete(r->right,z);}
            else {
                if (r->left == NULL && r->right == NULL) {return NULL;}
                else if (r->left == NULL) {return r->right;}
                else if (r->right == NULL) {return r->left;}
                TreeElement<T>* temp = Tminimum(r->right);
                r->value = temp->value;
                r->right = TDelete(r->right, temp->value);
            }
            return r;
        }
    public:
        Tree() {this->root = nullptr;}
        void insert(T element) {
            if (root==nullptr){
                root = new TreeElement<T> (element);}
            else {
                            ins(element);
            }
            }
        bool search (T element) {return isElem(element,root);}
        string draw() {return toS(root);}
        void del (T element) {root = TDelete(root,element);}
};