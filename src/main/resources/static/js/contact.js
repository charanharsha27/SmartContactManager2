const deleteContact = (id)=> {
    console.log(id);

    Swal.fire({
        title: "Are you sure?",
        text: "You won't be able to revert this!",
        icon: "warning",
        showCancelButton: true,
        confirmButtonColor: "#3085d6",
        cancelButtonColor: "#d33",
        confirmButtonText: "Yes, delete it!"
      }).then((result) => {
        if (result.isConfirmed) {
            const url = "http://localhost:8080/user/contacts/delete-contact/"+id;
            window.location.replace(url);
        }
        else{
            Swal.fire({
                title: "Cancelled!",
                text: "Contact hasn't been deleted.",
                icon: "error"
              });
        }
      });
}