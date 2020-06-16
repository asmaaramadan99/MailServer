#include <bits/stdc++.h>
using namespace std;

int main(){
    int t;
    cin >> t;
    while(t--){
        int n;
        cin >> n;
        int arr[n];
        vector<pair<int, int> > solutions;
        multiset<int> p1, p2;
        multiset<int>::iterator it;

        int flag = 0;

        ///contains the elements which are duplicated in the second set
        set<int> dup2;
        for (int i = 0;i < n;i++){
            cin >> arr[i];

            p2.insert(arr[i]);

            if (p2.count(arr[i]) == 2)
                dup2.insert(arr[i]);

            if (p2.count(arr[i]) == 3)
                flag = 1;
        }

        if (flag){
            cout << 0 << endl;
            continue;
        }
        /*cout << "P2 : ";
        for (it = p2.begin();it != p2.end();it++)
            cout << *it << " ";
        cout << endl;

        cout << "DUP2 : ";
        for (it = dup2.begin();it != dup2.end();it++)
            cout << *it << " ";
        cout << endl;*/

        for(int i = 0;i < n;i++){

            if (p1.count(arr[i]) >= 1)
                break;
            p1.insert(arr[i]);

            it = p2.find(arr[i]);
            p2.erase(it);

            if (p2.count(arr[i]) == 1)
                dup2.erase(arr[i]);

            /*cout << "P2 : ";
            for (it = p2.begin();it != p2.end();it++)
                cout << *it << " ";
            cout << endl;

            cout << "DUP2 : ";
            for (it = dup2.begin();it != dup2.end();it++)
                cout << *it << " ";
            cout << endl;*/

            if (n-i-1 == *p2.rbegin() && i+1 == *p1.rbegin()){
                if (dup2.empty()){
                    solutions.push_back(make_pair(i+1, n-i-1));
                }
            }
        }

        cout << solutions.size() << endl;

        for (int i = 0;i < solutions.size();i++)
            cout << solutions[i].first << " " << solutions[i].second << endl;
    }
    return 0;
}
