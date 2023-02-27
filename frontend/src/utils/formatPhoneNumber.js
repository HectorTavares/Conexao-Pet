export const formatPhoneNumber = (phoneNumber) => {
  const ddd = phoneNumber?.slice(0, 2);
  const number = phoneNumber?.slice(2);
  return `(${ddd}) ${number}`;
};
