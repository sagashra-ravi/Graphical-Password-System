USER REGISTRATION FORM CODING

using System;
using System.Data.SqlClient ; using System.Collections.Generic; using System.ComponentModel; using System.Data;
using System.Drawing; using System.Text;
using System.Windows.Forms;

namespace GraphicalPassword
{
public partial class frmUserRegistration : Form
{
public frmUserRegistration()
{
InitializeComponent();
}

private void button1_Click(object sender, EventArgs
e)
{

}
int x,y;
private void btnSave_Click(object sender, EventArgs
e)
{
if (cls.con.State != ConnectionState.Open)
cls.con.Open();
cls.cmd.CommandText = "Delete From Authentication Where UserName='" + txtUser.Text + "'";
cls.cmd.ExecuteNonQuery();

cls.cmd.CommandText = "Select Count(*) From Authentication Where UserName='" + txtUser.Text + "'";

//if (int.Parse(cls.cmd.ExecuteScalar().ToString()) == 1)
//{
 
//	MessageBox.Show("User Name Already Found.", "Note", MessageBoxButtons.OK, MessageBoxIcon.Information);
//	txtUser.Focus();
//	return;
//

//cls.cmd.CommandText = "insert into Admin Values ('" + txtUser.Text + "','" + txtPass. Text + "')";
//cls.cmd.ExecuteNonQuery();

int imgid = 0;
string[] tokens = null; int track;
if (checkBox1.Checked) track = 1;
else
track = 0;
for (int i = 0; i < checkedListBox1.Items.Count; i++)
{
 

true)
 
if (checkedListBox1.GetItemChecked(i) ==

{

tokens =
 
checkedListBox1.Items[i].ToString().Split( new char[] { ':'
});
 


{ ',' })[0]);

{ ',' })[1]);
 
imgid =int.Parse(tokens[0]); x=int.Parse( tokens [1].Split(new char[]

y = int.Parse(tokens[1].Split(new char[]


imgid =
 
int.Parse(checkedListBox1.Items[i].ToString().Split(new char[] { ':' })[0]);

cls.cmd.CommandText = "Insert into Authentication(UserName,Password,ImageId,XPercent,YPercent, TrackLocation) Values('" + txtUser.Text + "','" + txtPass.Text + "'," + imgid.ToString ()+ "," + x.ToString () + "," + y.ToString() + "," + track.ToString() + ")";
cls.cmd.ExecuteNonQuery();
}
}
 

MessageBox.Show("User Registration Completed.", "Note", MessageBoxButtons.OK, MessageBoxIcon.Information);
}

private void btnClose_Click(object sender, EventArgs e)
}

private void pictureBox1_Click(object sender, EventArgs e)
{


}

private void button1_Click_1(object sender, EventArgs e)
{
txtUser.Text = ""; txtPass.Text = ""; checkedListBox1.Items.Clear(); txtUser.Focus();

}

private void pictureBox1_MouseUp(object sender, MouseEventArgs e)
{
x=e.X;
y=e.Y;
string imageid = ((PictureBox)(sender)).Tag.ToString();
if (cls.con.State != ConnectionState.Open) cls.con.Open();
cls.cmd.CommandText="Select Extension From Images Where ImageId='" + imageid + "'";

System.Diagnostics.Process.Start(Application.StartupPath + "\\..\\..\\audio\\" + imageid.ToString() +"." + cls.cmd.ExecuteScalar().ToString());
 
bool found = false;
for (int i = 0; i < checkedListBox1.Items.Count; i++)
{
if (checkedListBox1.Items[i].ToString().StartsWith(imageid + ":"))
{
found = true; break;
}
}
