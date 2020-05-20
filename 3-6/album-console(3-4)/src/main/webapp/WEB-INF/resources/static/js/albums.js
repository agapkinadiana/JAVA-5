function editAlbum(id) {
    document.location.href = `albums/${id}/edit`;
}

function deleteAlbum(id) {
    fetch(`/albums/${id}`, {
        method: 'DELETE'
    }).then(response => {
        document.getElementById(`${id}-tr`).innerHTML = '';
    })
}