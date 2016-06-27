//THIS CODE DEMONSTRATES THE PRRxDISTANCE_IMPROVEMENT ROUTING PROTOCOL FOR WSNs
/*
#########################################################################################################
The code is implementation of a wireless sensor networks protocol called 
PRRxDistance vecotr with regular density in the network.
#########################################################################################################
*/


#include<iostream.h>
#include<conio.h>
#include<math.h>
#include<string.h>

class node
{
	public:

	int count,neighbor_node[6],data_size,no_of_packets;
	float prr12,E_residual,E_initial;
	float dist_improvement,prrXdist;

	void init_Energy();
	void init_neighbor_node(int j);
	void get_data_size();
	void calc_prr12();

	void calc_Eresidual_data();
	void calc_Eresidual_ack();
	void calc_dist_improvement(int x,int s,int d,int dist[10][10]);
	friend void calc_prrXdist(node n,int s,int x);
	friend void send_data(node n,int s,int sensor_node,int pkt_no);
	friend void display(int f);
};


void node::init_Energy()
{
	E_initial=0.62;
	E_residual=E_initial;
}

void node::init_neighbor_node(int j)
{
	switch(j)
	{
	case 0: neighbor_node[0]=1;
		neighbor_node[1]=2;
		neighbor_node[2]=3;
		count=3;
		break;

	case 1: neighbor_node[0]=0;
		neighbor_node[1]=3;
		neighbor_node[2]=9;
		count=3;
		break;

	case 2: neighbor_node[0]=0;
		neighbor_node[1]=3;
		neighbor_node[2]=4;
		count=3;
		break;

	case 3: neighbor_node[0]=0;
		neighbor_node[1]=1;
		neighbor_node[2]=2;
		neighbor_node[3]=4;
		neighbor_node[4]=5;
		neighbor_node[5]=9;
		count=6;
		break;

	case 4: neighbor_node[0]=2;
		neighbor_node[1]=3;
		neighbor_node[2]=5;
		neighbor_node[3]=7;
		count=4;
		break;


	case 5: neighbor_node[0]=3;
		neighbor_node[1]=4;
		neighbor_node[2]=6;
		neighbor_node[3]=7;
		neighbor_node[4]=8;
		neighbor_node[5]=9;
		count=6;
		break;


	case 6: neighbor_node[0]=5;
		neighbor_node[1]=8;
		neighbor_node[3]=9;
		count=3;
		break;

	case 7: neighbor_node[0]=4;
		neighbor_node[1]=5;
		neighbor_node[2]=8;
		count=3;
		break;

	case 8: neighbor_node[0]=5;
		neighbor_node[1]=6;
		neighbor_node[2]=7;
		count=3;
		break;

	case 9: neighbor_node[0]=1;
		neighbor_node[1]=3;
		neighbor_node[2]=5;
		neighbor_node[3]=6;
		count=4;
		break;

	}
}


void node::get_data_size()
{
	int packetsize=100,rem=0;
	cout<<endl<<"Enter the size of data to be transmitted: ";
	cin>>data_size;

	rem=data_size%packetsize;
	if(rem>0)
	      no_of_packets=data_size/packetsize+1;
	else
	      no_of_packets=data_size/packetsize;
}


void node::calc_prr12()
{
	float f=120.0,l=7.0,snr=15.8489,e=2.7172;

	prr12=pow(1-0.5*(pow(e,-snr/1.28)),16*f-8*l);

}


void node::calc_Eresidual_data()
{
	float Energy_per_packet=0.025;
	E_residual-=Energy_per_packet;
}



void node::calc_Eresidual_ack()
{
	float Energy_per_packet=0.002;
	E_residual-=Energy_per_packet;
}




void node::calc_dist_improvement(int x,int s,int d,int dist[10][10])
{
	int dist_nbr_dstn,dist_crt_dstn;

	dist_nbr_dstn=dist[x][d];
	dist_crt_dstn=dist[s][d];

	dist_improvement=1-(float)dist_nbr_dstn/dist_crt_dstn;

}



void calc_prrXdist(node n[10],int s,int x)
{
	n[x].prrXdist=n[s].prr12*n[x].dist_improvement;
}

void display(int f)
{
	switch(f)
	{
		case 0:
			cout<<"\n\n			.n1	.n9	.n6"<<
			      "\n\n\n	        *.n0	    .n3     .n5    .n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;
		case 2:
			cout<<"\n\n			.n1	.n9	.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5    .n8"<<
			      "\n\n\n		       *.n2	.n4    .n7\n\n";
		break;
		case 1:
			cout<<"\n\n		       *.n1	.n9	.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5    .n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;
		case 3:
			cout<<"\n\n			.n1	.n9	.n6"<<
			      "\n\n\n		 .n0	   *.n3     .n5    .n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;
		case 4:
			cout<<"\n\n			.n1	.n9	.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5    .n8"<<
			      "\n\n\n			.n2    *.n4    .n7\n\n";
		break;
		case 5:
			cout<<"\n\n			.n1	.n9	.n6"<<
			      "\n\n\n		 .n0	    .n3    *.n5    .n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;
		case 6:
			cout<<"\n\n			.n1	.n9    *.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5    .n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;
		case 7:
			cout<<"\n\n			.n1	.n9	.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5    .n8"<<
			      "\n\n\n			.n2	.n4    *.n7\n\n";
		break;
		case 8:
			cout<<"\n\n			.n1	.n9	.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5   *.n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;
		case 9:
			cout<<"\n\n			.n1    *.n9	.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5    .n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;
		default:
			cout<<"\n\n			.n1     .n9	.n6"<<
			      "\n\n\n		 .n0	    .n3     .n5    .n8"<<
			      "\n\n\n			.n2	.n4    .n7\n\n";
		break;

	}
}

void send_data(node n[10],int s,int sensor_node,int pkt_no)
{
	cout<<"\nPacket Number "<<pkt_no<<" sent to NODE "<<sensor_node;
	n[s].calc_Eresidual_data();
	cout<<"\nResidual Energy of Node "<<s<<" : "<<n[s].E_residual<<" J";

	cout<<"\n\nAT NODE:"<<sensor_node<<"\nPacket Received from NODE n"<<s
	    <<"\nACK sent to NODE: "<<s;
	n[sensor_node].calc_Eresidual_ack();
	cout<<"\nResidual Energy of Node "<<sensor_node<<" :"<<n[sensor_node].E_residual;

	display(sensor_node);

	getch();
	clrscr();

}

void main()
{
	clrscr();
	node n[10];
	int s,d,i,pkt_no=1,source;
	int dist[10][10]={0,10,10,10,17,20,25,25,30,17,
			10,0,17,10,20,17,20,25,25,10,
			10,17,0,10,10,17,25,20,25,20,
			10,10,10,0,10,10,17,17,20,10,
			17,20,10,10,0,10,20,10,17,17,
			20,17,17,10,10,0,10,10,10,10,
			25,20,25,17,20,10,0,17,10,10,
			25,25,20,17,10,10,17,0,10,20,
			30,25,25,20,17,10,10,10,0,17,
			17,10,20,10,17,10,10,20,17,0};

	cout<<"-* THIS DEMONSTRATES PRRxDISTANCE_IMPROVEMENT ROUTING PROTOCOL *-\n\n";
	display(99);
	cout<<"\nEnter source and destination node:";
	cin>>s>>d;
	source=s;

	if((s<10) && (d<10) && (s!=d))
	{
		for(i=0;i<10;i++)
		{
			n[i].init_Energy();
		}

		n[source].get_data_size();

	     while(pkt_no<=n[source].no_of_packets && n[source].E_residual>=0.025)
	     {
		clrscr();

		do
		{

		 cout<<"\nCurrent NODE: "<<s;
		 n[s].init_neighbor_node(s);
		 cout<<"\nNeighbor nodes:\n";

		 for(i=0;i<n[s].count;i++)
			cout<<" "<<n[s].neighbor_node[i];
		 display(s);

		 for(i=0;i<n[s].count;i++)
		 {
			if(n[s].neighbor_node[i]==d)
			{	if(n[d].E_residual>=0.025)
				{
					clrscr();
		    //			display(s);
					send_data(n,s,d,pkt_no);
					getch();
					goto xyz;
				}
				else
				{
					clrscr();
					cout<<"\nDestination Node is Dead"
					    <<"\nTransmission Aborted";
					goto xyz2;
				}


			 }
		 }

		 n[s].calc_prr12();

		 for(i=0;i<n[s].count;i++)
		 {
			int x=n[s].neighbor_node[i];
			n[x].calc_dist_improvement(x,s,d,dist);
			calc_prrXdist(n,s,x);

			cout<<"\nPRRxDIST_IMPROVEMENT of NODE n"<<x<<" = "<<n[x].prrXdist
			    <<"\n";
		  }

		  getch();
		  clrscr();

		  float temp1=0;
		  int sensor_node;

		 for(i=0;i<n[s].count;i++)
		  {
			int y=n[s].neighbor_node[i];
			if(n[y].prrXdist>temp1)
				{
					temp1=n[y].prrXdist;
					sensor_node=y;
				}
		  }

		  if(n[s].E_residual>=0.025)
		  {
		       if(n[sensor_node].E_residual>=0.002)
		       {
				send_data(n,s,sensor_node,pkt_no);
		       }
		       else
		       {
				clrscr();
				cout<<"\n\n\n\t\tThe neighbour node is dead"<<
				     "\t\tTRANSMISSION ABORTED";
				goto xyz2;
		       }
		  }
		  else
		  {
				clrscr();
				cout<<"\n\n\n\t\tThe current node is dead"<<
				     "\n\t\tTRANSMISSION ABORTED";
				goto xyz2;

		  }

		  s=sensor_node;
		  sensor_node=0;

		}

		  while(s!=d);
xyz:		  pkt_no++;
		  s=source;

	     }

xyz1:	     if(n[source].E_residual<0.025)
	     {
		clrscr();
		cout<<"\nSource node is dead"<<
		       "\nTransmission ABORTED";
	     }

	     else
	     {
		clrscr();
		cout<<"\n\n\n\n\t\t\tTRANSMISSION SUCCESSFULLY COMPLETED!!";
	     }
	}

	else
	{
		clrscr();
		cout<<"\nEnter a valid Source and Destination node!!" ;
	}

xyz2:	getch();
}