console.log("console is work");

const viewContactModal = document.getElementById("view_contact_modal");

const options = {
  placement: "bottom-right",
  backdrop: "dynamic",
  backdropClasses: "bg-gray-900/50 dark:bg-gray-900/80 fixed inset-0 z-40",
  closable: true,
  onHide: () => {
    console.log("modal is hidden");
  },
  onShow: () => {
    setTimeout(() => {
      viewContactModal.classList.add("scale-100");
    }, 50);
  },
  onToggle: () => {
    console.log("modal has been toggled");
  },
};

const instanceOptions = {
  id: "view_contact_modal",
  override: true,
};

const contactModal = new Modal(viewContactModal, options, instanceOptions);

// ✅ Expose functions to global scope for onclick to access
window.openContactModal = function () {
  contactModal.show();
};

window.closeContactModal = function () {
  contactModal.hide();
};
